package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;


import br.com.senacsp.tads.stads4ma.library.domainmodel.Role;
import br.com.senacsp.tads.stads4ma.library.domainmodel.RoleType;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.RoleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class RoleRepositoryImpl implements RoleRepository {

    private final Set<Role> internalData = new HashSet<>();

    @Override
    public <S extends Role> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(internalData);
    }

    @Override
    public List<Role> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return internalData.stream()
                .filter(role -> role.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Role> findByType(RoleType type) {
        return internalData.stream()
                .filter(role -> role.getType() == type)
                .findFirst();
    }

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            role.setId(UUID.randomUUID());
        }
        internalData.removeIf(r -> r.getId().equals(role.getId()));
        internalData.add(role);
        return role;
    }

    @Override
    public void deleteById(UUID id) {
        internalData.removeIf(role -> role.getId().equals(id));
    }

    @Override
    public void delete(Role entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Role> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(UUID id) {
        return internalData.stream().anyMatch(role -> role.getId().equals(id));
    }

    @Override
    public long count() {
        return internalData.size();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Role> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Role> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Role> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Role getOne(UUID uuid) {
        return null;
    }

    @Override
    public Role getById(UUID uuid) {
        return null;
    }

    @Override
    public Role getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Role> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Role> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Role> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Role, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Role> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return null;
    }
}