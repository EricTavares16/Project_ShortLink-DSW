package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.QLink;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class LinkRepositoryImpl  {

    private final JPAQueryFactory queryFactory;

    public LinkRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    public List<Link> findLinksByUser(UUID userId) {
        QLink link = QLink.link;

        return queryFactory
                .selectFrom(link)
                .where(link.user.id.eq(userId))
                .fetch();
    }
/*
    @Override
    public void flush() {

    }

    @Override
    public void deleteAllInBatch(Iterable<Link> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }
    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<Link> findAll() {
        return List.of();
    }

    @Override
    public List<Link> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Link entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Link> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Link> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Link> findAll(Pageable pageable) {
        return null;
    }*/
}
