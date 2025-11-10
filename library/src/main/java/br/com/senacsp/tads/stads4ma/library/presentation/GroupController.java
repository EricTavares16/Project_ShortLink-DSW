package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.models.AddMemberRequest;
import br.com.senacsp.tads.stads4ma.library.models.GroupRequest;
import br.com.senacsp.tads.stads4ma.library.service.GroupService;
import br.com.senacsp.tads.stads4ma.library.application.dto.GroupDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador responsável pela gestão de grupos.
 * - Criação e listagem de grupos.
 * - Adição e remoção de membros.
 * - Visualização de links do grupo.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = groupService.findAllDTO();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable UUID id) {
        GroupDTO group = groupService.findDTOById(id);
        return group != null ? ResponseEntity.ok(group) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO groupDTO) {
        GroupDTO created = groupService.createDTO(groupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable UUID id, @RequestBody GroupDTO groupDTO) {
        GroupDTO updated = groupService.updateDTO(id, groupDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean deleted = groupService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


//    /**
//     * @apiNote Lista todos os grupos que o usuário autenticado participa.
//     * @return 200 OK com lista de grupos.
//     */
//    @GetMapping
//    public ResponseEntity<?> listGroups() {
//        // TODO: busca grupos do usuário autenticado
//        return ResponseEntity.ok("Lista de grupos do usuário");
//    }
//
//    /**
//     * @apiNote Cria um novo grupo.
//     * @param request Contém o nome do grupo.
//     * @return 201 Created.
//     */
//    @PostMapping
//    public ResponseEntity<?> createGroup(@RequestBody GroupRequest request) {
//        // TODO: cria grupo e adiciona o criador como membro
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body("Grupo criado");
//    }
//
//    /**
//     * @apiNote Adiciona um membro ao grupo.
//     * @param groupId ID do grupo.
//     * @param request Contém o ID do usuário a ser adicionado.
//     * @return 200 OK.
//     */
//    @PostMapping("/{groupId}/members")
//    public ResponseEntity<?> addMember(
//            @PathVariable UUID groupId,
//            @RequestBody AddMemberRequest request
//    ) {
//        // TODO: valida se o user autenticado pertence ao grupo
//        return ResponseEntity.ok("Membro adicionado");
//    }
//
//    /**
//     * @apiNote Remove um membro do grupo.
//     * @param groupId ID do grupo.
//     * @param userId ID do usuário a remover.
//     * @return 200 OK.
//     */
//    @DeleteMapping("/{groupId}/members/{userId}")
//    public ResponseEntity<?> removeMember(
//            @PathVariable UUID groupId,
//            @PathVariable UUID userId
//    ) {
//        // TODO: valida permissões e remove
//        return ResponseEntity.ok("Membro removido");
//    }
//
//    /**
//     * @apiNote Lista os links pertencentes a um grupo.
//     * @param groupId ID do grupo.
//     * @return 200 OK com lista de links do grupo.
//     */
//    @GetMapping("/{groupId}/links")
//    public ResponseEntity<?> listGroupLinks(@PathVariable UUID groupId) {
//        // TODO: busca links do grupo
//        return ResponseEntity.ok("Links do grupo");
//    }
}

