package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.MovementHistoryDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistory;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistoryId;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.LinkRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.MovementHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovementHistoryServiceImpl implements MovementHistoryService {

    private final MovementHistoryRepository movementHistoryRepository;
    private final LinkRepository linkRepository;

    @Override
    public List<MovementHistoryDTO> findAll() {
        return movementHistoryRepository.findAll().stream()
                .map(MovementHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovementHistoryDTO> findById(MovementHistoryId id) {
        return movementHistoryRepository.findById(id)
                .map(MovementHistoryDTO::fromEntity);
    }

    @Override
    public List<MovementHistoryDTO> findByLinkId(UUID linkId) {
        return movementHistoryRepository.findByLink_Id(linkId).stream()
                .map(MovementHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovementHistoryDTO> findByAction(String action) {
        return movementHistoryRepository.findByAction(action).stream()
                .map(MovementHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MovementHistoryDTO create(MovementHistoryDTO request) {
        Link link = linkRepository.findById(request.getLinkId())
                .orElseThrow(() -> new RuntimeException("Link não encontrado para o ID: " + request.getLinkId()));

        MovementHistory movement = toEntity(request, link);

        MovementHistory saved = movementHistoryRepository.save(movement);

        return MovementHistoryDTO.fromEntity(saved);
    }

    /**
     * Converte MovementHistoryDTO para a entidade MovementHistory, gerando o UUID único
     * para a chave composta.
     */
    private MovementHistory toEntity(MovementHistoryDTO dto, Link link) {
        if (dto == null) return null;

        // CORREÇÃO: Criação do ID composto com os 3 argumentos: linkId, data e sequenceId (UUID aleatório)
        MovementHistoryId newId = new MovementHistoryId(
                link.getId(),
                LocalDate.now(),
                UUID.randomUUID()
        );

        return MovementHistory.builder()
                .id(newId) // Usa o novo ID composto
                .link(link)
                .createdAt(LocalDateTime.now())
                .totalClicks(dto.getTotalClicks() != null ? dto.getTotalClicks() : 1)
                .action(dto.getAction())
                .description(dto.getDescription())
                .userName(dto.getUserName())
                .build();
    }

    @Override
    public boolean deleteById(MovementHistoryId id) {
        if (movementHistoryRepository.existsById(id)) {
            movementHistoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}