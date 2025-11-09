package br.com.senacsp.tads.stads4ma.library.service;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Group;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.LinkRepository;
import br.com.senacsp.tads.stads4ma.library.application.dto.LinkDTO;
import br.com.senacsp.tads.stads4ma.library.models.LinkRequest;
import br.com.senacsp.tads.stads4ma.library.service.LinkService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public LinkDTO createLink(LinkRequest request, UUID userId) {
        Link link = Link.builder()
                .shortCode(generateShortCode()) // Exemplo de geração simples
                .originalUrl(request.originalUrl())
                .createdAt(LocalDate.now())
                .expiresAt(request.expiresAt() != null ? LocalDate.parse(request.expiresAt()) : null)
                .isActive(request.isActive() != null ? request.isActive() : true)
                .user(User.builder().id(userId).build())
                .group(request.groupId() != null ? Group.builder().id(request.groupId()).build() : null)
                .build();

        Link saved = linkRepository.save(link);
        return toDTO(saved);
    }

    @Override
    public List<LinkDTO> getLinksByUser(UUID userId) {
        return linkRepository.findLinksByUser(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LinkDTO getLinkById(UUID linkId) {
        return linkRepository.findById(linkId)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Link não encontrado"));
    }

    @Override
    public void deleteLink(UUID linkId) {
        if (!linkRepository.existsById(linkId)) {
            throw new RuntimeException("Link não encontrado");
        }
        linkRepository.deleteById(linkId);
    }

    private String generateShortCode() {
        // Apenas exemplo; ideal gerar de forma mais robusta
        return UUID.randomUUID().toString().substring(0, 8);
    }

    // Conversão manual para DTO
    private LinkDTO toDTO(Link link) {
        return new LinkDTO(
                link.getId(),
                link.getShortCode(),
                link.getOriginalUrl(),
                link.getCreatedAt(),
                link.getExpiresAt(),
                link.isActive(),
                link.getUser() != null ? link.getUser().getId() : null,
                link.getGroup() != null ? link.getGroup().getId() : null
        );
    }
}
