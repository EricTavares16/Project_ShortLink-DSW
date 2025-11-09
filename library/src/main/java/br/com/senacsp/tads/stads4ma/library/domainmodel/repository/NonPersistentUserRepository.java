package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class NonPersistentUserRepository implements UserRepository {


    private final Set<User> internalData = new HashSet<>();


    @Override
    public long count() {
        return internalData.size();
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> findByEmail(String email) {

        return internalData.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .toList();
    }

    public User create(User user) {
        user.setId(UUID.randomUUID()); // se você quiser gerar id aqui
        internalData.add(user);
        return user;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID()); // gera ID se não tiver
        }
        internalData.add(user); // adiciona no "banco"
        return user;
    }
/*
    @Override
    public User update(User user) {
        deleteById(user.getId());
        internalData.add(user);
        return user;
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }

    @Override
    public User findById(UUID id) {
        return internalData.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
*/
    @Override
    public List<User> findByNameAndEmail(String name, String email) {
        return internalData.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name)
                        && u.getEmail().equalsIgnoreCase(email))
                .toList();
    }

    @Override
    public List<User> findByLinks(List<Link> link) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(internalData);
    }

    @Override
    public List<User> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public boolean existsById(UUID id) {
        return internalData.stream().anyMatch(u -> u.getId().equals(id));
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(UUID uuid) {
        return null;
    }

    @Override
    public User getById(UUID uuid) {
        return null;
    }

    @Override
    public User getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

/*
    public boolean removeById(UUID id){
        if (this.existsById(id));{
            User u = this.findById(id);
            this.internalData.remove(id);
        }
        return false;
    }*/

}