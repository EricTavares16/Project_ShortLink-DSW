package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.application.dto.MovementHistoryDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistory;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistoryId;
import br.com.senacsp.tads.stads4ma.library.service.MovementHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para histórico de movimentações do usuário.
 * - Registra e lista ações CRUD (link criado, atualizado, etc.)
 */
@RestController
@RequestMapping("/history")
public class MovementHistoryController {

    /**
     * @apiNote Lista o histórico de ações do usuário autenticado.
     * @param entity (opcional) filtra por tipo (link, group, user).
     * @return 200 OK com histórico.
     *//*
    @GetMapping
    public ResponseEntity<?> listHistorys(
            @RequestParam(required = false) String entity
    ) {
        // TODO: busca histórico do usuário autenticado
        return ResponseEntity.ok("Histórico de movimentações");
    }*/
    private final MovementHistoryService service;

    public MovementHistoryController(MovementHistoryService service) {
        this.service = service; // <-- Inicialização do campo
    }

    private MovementHistoryDTO toDTO(MovementHistory h) {
        return MovementHistoryDTO.builder()
                .linkId(h.getId().getLinkId())
                .date(h.getId().getDate())
                .action(h.getAction())
                .description(h.getDescription())
                .userName(h.getUserName())
                .build();
    }

    private MovementHistory toEntity(MovementHistoryDTO dto) {
        return MovementHistory.builder()
                .id(new MovementHistoryId(dto.getLinkId(), dto.getDate()))
                .action(dto.getAction())
                .description(dto.getDescription())
                .userName(dto.getUserName())
                .build();
    }

    @GetMapping
    public ResponseEntity<List<MovementHistoryDTO>> listHistory(
            @RequestParam(required = false) String entity
    ) {
        List<MovementHistoryDTO> history = service.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(history);
    }

    @PostMapping
    public ResponseEntity<MovementHistoryDTO> create(@RequestBody MovementHistoryDTO dto) {
        MovementHistory created = service.save(toEntity(dto));
        return ResponseEntity.ok(toDTO(created));
    }
}

