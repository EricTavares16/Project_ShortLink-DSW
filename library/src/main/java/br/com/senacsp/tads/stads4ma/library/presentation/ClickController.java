package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.application.dto.ClickDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Click;
import br.com.senacsp.tads.stads4ma.library.service.ClickService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controlador para registro e consulta de cliques nos links.
 */
@RestController
@RequestMapping("/links")
public class ClickController {

    private final ClickService clickService;

    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    private ClickDTO toDTO(Click click) {
        return ClickDTO.builder()
                .id(click.getId())
                .linkId(click.getLink().getId())
                .date(click.getDate())
                .clickedAt(click.getClickedAt())
                .region(click.getRegion())
                .city(click.getCity())
                .device(click.getDevice())
                .referer(click.getReferer())
                .build();
    }

    @GetMapping("/{linkId}/clicks")
    public ResponseEntity<List<ClickDTO>> listClicks(@PathVariable UUID linkId) {
        List<ClickDTO> clicks = clickService.findByLink(linkId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clicks);
    }
}

