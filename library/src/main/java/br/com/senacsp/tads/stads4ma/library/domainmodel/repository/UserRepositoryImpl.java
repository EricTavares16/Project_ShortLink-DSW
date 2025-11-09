package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.QLink;
import br.com.senacsp.tads.stads4ma.library.domainmodel.QProfile;
import br.com.senacsp.tads.stads4ma.library.domainmodel.QUser;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    // Instâncias estáticas dos QTypes geradas pelo QueryDSL
    private final QUser user = QUser.user;
    private final QLink link = QLink.link;
    private final QProfile profile = QProfile.profile;

    /**
     * Retorna um usuário específico com o Profile e Links carregados (usando Criteria API).
     */
    public Optional<User> findByIdWithProfileAndLinksCriteria(UUID id) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        // Faz o fetch para trazer profile e links junto
        root.fetch("profile", JoinType.LEFT);
        root.fetch("links", JoinType.LEFT);

        criteriaQuery.select(root)
                .distinct(true)
                .where(builder.equal(root.get("id"), id));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        List<User> resultSet = query.getResultList();

        return resultSet.stream().findFirst();
    }

    /**
     * Busca usuários que possuem pelo menos X links e cujo nome contenha uma parte específica (Criteria API).
     */
    public List<User> findByMinLinksAndNameLikeCriteria(int minLinks, String namePart) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root)
                .where(
                        builder.and(
                                builder.greaterThanOrEqualTo(
                                        builder.size(root.get("links")), minLinks
                                ),
                                builder.like(
                                        builder.lower(root.get("name")),
                                        "%" + namePart.toLowerCase() + "%"
                                )
                        )
                )
                .orderBy(builder.asc(root.get("name")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Cria uma instância da fábrica do QueryDSL.
     */
    private JPAQueryFactory qf() {
        return new JPAQueryFactory(this.entityManager);
    }

    /**
     * Retorna um usuário com o Profile e Links carregados (usando QueryDSL).
     */
    public Optional<User> findByIdWithProfileAndLinksQueryDsl(UUID id) {
        return this.qf()
                .selectFrom(user)
                .leftJoin(user.profile, profile).fetchJoin()
                .leftJoin(user.links, link).fetchJoin()
                .where(user.id.eq(id))
                .distinct()
                .fetch()
                .stream()
                .findFirst();
    }

    /**
     * Busca usuários com nome parecido e pelo menos X links (usando QueryDSL).
     */
    public List<User> findByLinksAndNameLikeQueryDsl(int minLinks, String namePart) {
        return this.qf()
                .select(user)
                .from(user)
                .leftJoin(user.links, link)
                .where(user.name.containsIgnoreCase(namePart))
                .groupBy(user.id)
                .having(link.id.count().goe(minLinks))
                .orderBy(user.name.asc())
                .fetch();
    }
}
