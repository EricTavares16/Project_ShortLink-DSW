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
public class NonPersistentUserRepository implements UserRepository<User,UUID> {


    private final Set<User> internalData = new HashSet<>();


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
    public List<User> findAll() {
        return new ArrayList<>(internalData);
    }

    @Override
    public boolean existsById(UUID id) {
        return internalData.stream().anyMatch(u -> u.getId().equals(id));
    }


    public boolean removeById(UUID id){
        if (this.existsById(id));{
            User u = this.findById(id);
            this.internalData.remove(id);
        }
        return false;
    }

}
