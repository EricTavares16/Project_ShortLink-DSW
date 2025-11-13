package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.application.dto.ClickDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Click;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.LinkRepository;
import br.com.senacsp.tads.stads4ma.library.service.ClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class ClickController {

    private final ClickService clickService;
    private final LinkRepository linkRepository;

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

    private Click toEntity(ClickDTO dto) {
        Link link = linkRepository.findById(dto.getLinkId())
                .orElseThrow(() -> new RuntimeException("Link não encontrado"));

        return Click.builder()
                .id(dto.getId())
                .link(link)
                .date(dto.getDate())
                .clickedAt(dto.getClickedAt())
                .region(dto.getRegion())
                .city(dto.getCity())
                .device(dto.getDevice())
                .referer(dto.getReferer())
                .build();
    }

    // LISTAR CLIQUES POR LINK
    @GetMapping("/{linkId}/clicks")
    public ResponseEntity<List<ClickDTO>> listClicks(@PathVariable UUID linkId) {
        List<ClickDTO> clicks = clickService.findByLink(linkId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clicks);
    }

    // REGISTRAR UM CLIQUE
    @PostMapping("/{linkId}/clicks")
    public ResponseEntity<ClickDTO> createClick(@PathVariable UUID linkId, @RequestBody ClickDTO dto) {

        Click click = Click.builder()
                .link(Link.builder().id(linkId).build()) // apenas referencia
                .date(dto.getDate())
                .clickedAt(dto.getClickedAt())
                .region(dto.getRegion())
                .city(dto.getCity())
                .device(dto.getDevice())
                .referer(dto.getReferer())
                .build();

        Click saved = clickService.save(click);

        return ResponseEntity.ok(toDTO(saved));
    }
}
