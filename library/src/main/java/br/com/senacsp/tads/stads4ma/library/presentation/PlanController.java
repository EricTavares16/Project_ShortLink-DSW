package br.com.senacsp.tads.stads4ma.library.presentation;


import br.com.senacsp.tads.stads4ma.library.application.dto.PlanDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Plan;
import br.com.senacsp.tads.stads4ma.library.domainmodel.PlanType;
import br.com.senacsp.tads.stads4ma.library.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private PlanDTO toDTO(Plan plan) {
        return PlanDTO.builder()
                .id(plan.getId())
                .type(plan.getType())
                .price(plan.getPrice())
                .maxLinks(plan.getMaxLinks())
                .build();
    }

    private Plan toEntity(PlanDTO dto) {
        return Plan.builder()
                .id(dto.getId())
                .type(dto.getType())
                .price(dto.getPrice())
                .maxLinks(dto.getMaxLinks())
                .build();
    }

    @GetMapping
    public ResponseEntity<List<PlanDTO>> findAll() {
        List<PlanDTO> plans = planService.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> findById(@PathVariable UUID id) {
        return planService.findById(id)
                .map(plan -> ResponseEntity.ok(toDTO(plan)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<PlanDTO> findByType(@PathVariable String type) {
        return planService.findByType(type)
                .map(plan -> ResponseEntity.ok(toDTO(plan)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlanDTO> create(@RequestBody PlanDTO dto) {
        Plan created = planService.create(toEntity(dto));
        return ResponseEntity.ok(toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> update(@PathVariable UUID id, @RequestBody PlanDTO dto) {
        Plan updated = planService.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean deleted = planService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}