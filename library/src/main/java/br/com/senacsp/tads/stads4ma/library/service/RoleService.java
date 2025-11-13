package br.com.senacsp.tads.stads4ma.library.service;


import br.com.senacsp.tads.stads4ma.library.domainmodel.Role;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(UUID id);
    Optional<Role> findByType(String type); // ← muda aqui
    Role create(Role role);
    Role update(UUID id, Role updatedRole);
    boolean deleteById(UUID id);
    boolean existsById(UUID id);
}