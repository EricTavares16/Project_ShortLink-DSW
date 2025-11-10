package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.application.dto.MovementHistoryDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistoryId;
import br.com.senacsp.tads.stads4ma.library.service.MovementHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movement-history")
public class MovementHistoryController {

    private final MovementHistoryService movementHistoryService;

    public MovementHistoryController(MovementHistoryService movementHistoryService) {
        this.movementHistoryService = movementHistoryService;
    }

    // Buscar todos
    @GetMapping
    public ResponseEntity<List<MovementHistoryDTO>> findAll() {
        return ResponseEntity.ok(movementHistoryService.findAll());
    }

    // Buscar por link (Mais usado)
    @GetMapping("/link/{linkId}")
    public ResponseEntity<List<MovementHistoryDTO>> findByLinkId(@PathVariable UUID linkId) {
        return ResponseEntity.ok(movementHistoryService.findByLinkId(linkId));
    }

    // Buscar por ação
    @GetMapping("/action/{action}")
    public ResponseEntity<List<MovementHistoryDTO>> findByAction(@PathVariable String action) {
        return ResponseEntity.ok(movementHistoryService.findByAction(action));
    }

    // Criar (Recebe DTO e retorna DTO)
    @PostMapping
    public ResponseEntity<MovementHistoryDTO> create(@RequestBody MovementHistoryDTO movementHistoryDTO) {
        MovementHistoryDTO createdDto = movementHistoryService.create(movementHistoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    /**
     * Endpoint para buscar por ID Completo (3 Componentes: linkId, date, sequenceId).
     */
    @GetMapping("/{linkId}/{date}/{sequenceId}")
    public ResponseEntity<MovementHistoryDTO> findById(
            @PathVariable UUID linkId,
            @PathVariable LocalDate date,
            @PathVariable UUID sequenceId) {

        // CORREÇÃO: Passando os 3 argumentos
        MovementHistoryId id = new MovementHistoryId(linkId, date, sequenceId);

        return movementHistoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint de deleção: Precisa dos 3 componentes da chave.
     */
    @DeleteMapping("/{linkId}/{date}/{sequenceId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID linkId,
            @PathVariable LocalDate date,
            @PathVariable UUID sequenceId) {

        // CORREÇÃO: Passando os 3 argumentos
        MovementHistoryId id = new MovementHistoryId(linkId, date, sequenceId);
        boolean deleted = movementHistoryService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}