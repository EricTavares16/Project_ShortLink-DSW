package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Profile;
import br.com.senacsp.tads.stads4ma.library.domainmodel.QProfile;
import br.com.senacsp.tads.stads4ma.library.domainmodel.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfileRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    private final QProfile qProfile = QProfile.profile;
    private final QUser qUser = QUser.user;

    /**
     * Busca um perfil pelo ID, já trazendo o usuário associado.
     */
    public Optional<Profile> findByIdWithUser(UUID id) {
        Profile profile = queryFactory()
                .selectFrom(qProfile)
                .leftJoin(qProfile.user, qUser).fetchJoin()
                .where(qProfile.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(profile);
    }

    /**
     * Busca todos os perfis de um determinado país.
     */
    public List<Profile> findByCountry(String country) {
        return queryFactory()
                .selectFrom(qProfile)
                .where(qProfile.country.equalsIgnoreCase(country))
                .orderBy(qProfile.fullName.asc())
                .fetch();
    }

    /**
     * Busca perfis com uma determinada preferência de tema (dark, light etc).
     */
    public List<Profile> findByThemePreference(String theme) {
        return queryFactory()
                .selectFrom(qProfile)
                .where(qProfile.themePreference.equalsIgnoreCase(theme))
                .fetch();
    }
}
