package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.application.dto.RoleDTO;
import br.com.senacsp.tads.stads4ma.library.service.RoleService;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    // 🔹 Converter manualmente Role -> RoleDTO
    private RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .type(role.getType())
                .build();
    }

    // 🔹 Converter manualmente RoleDTO -> Role
    private Role toEntity(RoleDTO dto) {
        return Role.builder()
                .id(dto.getId())
                .type(dto.getType())
                .build();
    }

    // 🔹 Criar uma nova Role
    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO dto) {
        Role role = toEntity(dto);
        Role saved = roleService.create(role);
        return ResponseEntity.ok(toDTO(saved));
    }

    // 🔹 Listar todas as Roles
    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        List<RoleDTO> roles = roleService.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    // 🔹 Buscar Role por ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable UUID id) {
        return roleService.findById(id)
                .map(role -> ResponseEntity.ok(toDTO(role)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Atualizar Role
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable UUID id, @RequestBody RoleDTO dto) {
        Role updated = roleService.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(updated));
    }

    // 🔹 Deletar Role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
