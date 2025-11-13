package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Role;
import br.com.senacsp.tads.stads4ma.library.domainmodel.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByType(RoleType type);
    Optional<Role> findByName(String name);
}
