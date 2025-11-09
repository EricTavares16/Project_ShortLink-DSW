package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.application.dto.LinkDTO;
import br.com.senacsp.tads.stads4ma.library.models.AddLinkToGroupRequest;
import br.com.senacsp.tads.stads4ma.library.models.LinkRequest;
import br.com.senacsp.tads.stads4ma.library.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador responsável pelos CRUDs de Links.
 * - Cada link pertence a um criador (user_id)
 * - Pode ou não pertencer a um grupo (group_id opcional)
 */
@RestController
@RequestMapping("api/links")
public class LinkController {

    /**
     * @apiNote Lista todos os links do usuário autenticado.
     * @param groupId (opcional) filtra links de um grupo específico.
     * @param active (opcional) filtra por status ativo/inativo.
     * @return 200 OK com lista de links.
     */
    @GetMapping
    public ResponseEntity<?> listLinks(
            @RequestParam(required = false) UUID groupId,
            @RequestParam(required = false) Boolean active
    ) {
        // TODO: busca os links conforme filtros e permissões
        return ResponseEntity.ok("Lista de links do usuário");
    }

    /*
     * @apiNote Cria um novo link (respeitando limite do plano).
     * @param request Contém URL original e (opcionalmente) o grupo.
     * @return 201 Created com o link encurtado.

    @PostMapping
    public ResponseEntity<?> createLink(@RequestBody LinkRequest request) {
        // TODO: valida plano, gera shortCode e salva
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Link criado com sucesso");
    }*/

    /**
     * @apiNote Atualiza um link existente do usuário autenticado.
     * @param id ID do link a ser atualizado.
     * @param request Campos a atualizar (URL original, ativo, expiração).
     * @return 200 OK com link atualizado.
     *//*
    @PatchMapping("/{id}")
    public ResponseEntity<?> save(
            @PathVariable UUID id,
            @RequestBody LinkRequest request
    ) {
        // TODO: verifica se link pertence ao usuário
        return ResponseEntity.ok("Link atualizado com sucesso");
    }

    /**
     * @apiNote Deleta um link criado pelo usuário autenticado.
     * @param id ID do link.
     * @return 204 No Content.
     *//*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable UUID id) {
        // TODO: verifica propriedade e exclui
        return ResponseEntity.noContent().build();
    }*/

    /**
     * @apiNote Adiciona um link existente a um grupo.
     * @param id ID do link.
     * @param request Contém o ID do grupo destino.
     * @return 200 OK.
     */
    @PostMapping("/{id}/add-to-group")
    public ResponseEntity<?> addToGroup(
            @PathVariable UUID id,
            @RequestBody AddLinkToGroupRequest request
    ) {
        // TODO: verifica se user é membro e associa link ao grupo
        return ResponseEntity.ok("Link adicionado ao grupo");
    }

    /**
     * @apiNote Remove um link de um grupo.
     * @param id ID do link.
     * @return 200 OK.
     */
    @DeleteMapping("/{id}/remove-from-group")
    public ResponseEntity<?> removeFromGroup(@PathVariable UUID id) {
        // TODO: remove associação de grupo
        return ResponseEntity.ok("Link removido do grupo");
    }
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<LinkDTO> create(
            @PathVariable UUID userId,
            @RequestBody LinkRequest request
    ) {
        LinkDTO link = linkService.createLink(request, userId);
        return ResponseEntity.ok(link);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LinkDTO>> listLinksByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(linkService.getLinksByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkDTO> getLink(@PathVariable UUID id) {
        return ResponseEntity.ok(linkService.getLinkById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}

