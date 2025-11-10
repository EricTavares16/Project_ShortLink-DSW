package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.MovementHistoryDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistory;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovementHistoryService {

    // Retorna DTOs
    List<MovementHistoryDTO> findAll();

    // Retorna DTO
    Optional<MovementHistoryDTO> findById(MovementHistoryId id);

    // Retorna DTOs
    List<MovementHistoryDTO> findByLinkId(UUID linkId);

    // Retorna DTOs
    List<MovementHistoryDTO> findByAction(String action);

    // Remove o método de criação que recebia a Entidade

    // Novo método de criação que recebe e retorna DTO
    MovementHistoryDTO create(MovementHistoryDTO request);

    // Mantém o retorno booleano para deleção
    boolean deleteById(MovementHistoryId id);
}
