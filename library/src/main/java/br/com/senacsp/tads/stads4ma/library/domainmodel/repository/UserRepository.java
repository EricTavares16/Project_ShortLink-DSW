package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;


import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID> {

    List<User> findByNameAndEmail(String name, String email);

    Optional<User> findByEmail(String email);

    List<User> findByLinks(List<Link> links);


    boolean existsById(UUID id);

}