package br.com.senacsp.tads.stads4ma.library.service;


import br.com.senacsp.tads.stads4ma.library.domainmodel.Role;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class    RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByType(String type) {
        return roleRepository.findByType(type);
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(UUID id, Role updatedRole) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setType(updatedRole.getType());
                    return roleRepository.save(existingRole);
                })
                .orElseThrow(() -> new RuntimeException("Função não encontrada para atualização"));
    }

    @Override
    public boolean deleteById(UUID id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return roleRepository.existsById(id);
    }
}