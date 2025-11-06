package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;


import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository<T, ID> {

    User save(User user);      // serve como create
    User update(User user);    // update
    boolean deleteById(UUID id);
    public User findById(UUID id);
    public List<User> findByNameAndEmail(String name, String email);
    public List<User> findByEmail(String email);
    public List<User> findByLinks(List<Link> link);
    List<User> findAll();
    boolean existsById(UUID id);




}
