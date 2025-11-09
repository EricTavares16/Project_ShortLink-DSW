package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Click;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Primary
@Component
public class ClickRepositoryImpl implements ClickRepository {

    private final @Qualifier Map<UUID, Click> db = new HashMap<>();

    @Override
    public List<Click> findByLink_Id(UUID linkId) {
        return db.values().stream()
                .filter(c -> c.getLink() != null && linkId.equals(c.getLink().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Click> findByCity(String city) {
        return db.values().stream()
                .filter(c -> city.equalsIgnoreCase(c.getCity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Click> findByRegion(String region) {
        return db.values().stream()
                .filter(c -> region.equalsIgnoreCase(c.getRegion()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Click> findByDevice(String device) {
        return db.values().stream()
                .filter(c -> device.equalsIgnoreCase(c.getDevice()))
                .collect(Collectors.toList());
    }

    @Override
    public <S extends Click> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Click> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public List<Click> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public Optional<Click> findById(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public Click save(Click entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(UUID id) {
        db.remove(id);
    }

    @Override
    public void delete(Click entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Click> entities) {

    }

    @Override
    public boolean existsById(UUID id) {
        return db.containsKey(id);
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteAll() {
        db.clear();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Click> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Click> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Click> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Click getOne(UUID uuid) {
        return null;
    }

    @Override
    public Click getById(UUID uuid) {
        return null;
    }

    @Override
    public Click getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Click> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Click> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Click> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Click> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Click> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Click> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Click, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Click> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Click> findAll(Pageable pageable) {
        return null;
    }

    // Métodos JpaRepository não usados aqui podem lançar exceção ou retornar vazio.
}
