package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Plan;

import br.com.senacsp.tads.stads4ma.library.domainmodel.PlanType;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.PlanRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class PlanRepositoryImpl implements PlanRepository {

    private final Set<Plan> internalData = new HashSet<>();

    @Override
    public <S extends Plan> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Plan> findAll() {
        return new ArrayList<>(internalData);
    }

    @Override
    public List<Plan> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public Optional<Plan> findById(UUID id) {
        return internalData.stream()
                .filter(plan -> plan.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Plan> findByType(PlanType type) {
        return internalData.stream()
                .filter(plan -> plan.getType() == type)
                .findFirst();
    }

    @Override
    public Plan save(Plan plan) {
        if (plan.getId() == null) {
            plan.setId(UUID.randomUUID());
        }
        internalData.removeIf(p -> p.getId().equals(plan.getId()));
        internalData.add(plan);
        return plan;
    }

    @Override
    public void deleteById(UUID id) {
        internalData.removeIf(plan -> plan.getId().equals(id));
    }

    @Override
    public void delete(Plan entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Plan> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(UUID id) {
        return internalData.stream().anyMatch(plan -> plan.getId().equals(id));
    }

    @Override
    public long count() {
        return internalData.size();
    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends Plan> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Plan> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Plan> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Plan getOne(UUID uuid) {
        return null;
    }

    @Override
    public Plan getById(UUID uuid) {
        return null;
    }

    @Override
    public Plan getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Plan> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Plan> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Plan> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Plan> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Plan> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Plan> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Plan, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Plan> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Plan> findAll(Pageable pageable) {
        return null;
    }
}