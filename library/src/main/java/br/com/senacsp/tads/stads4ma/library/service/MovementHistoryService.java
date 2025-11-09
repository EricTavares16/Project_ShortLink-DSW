package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistory;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistoryId;

import java.util.List;
import java.util.Optional;

public interface MovementHistoryService {

    List<MovementHistory> findAll();

    Optional<MovementHistory> findById(MovementHistoryId id);

    List<MovementHistory> findByLink(java.util.UUID linkId);

    List<MovementHistory> findByAction(String action);

    MovementHistory save(MovementHistory history);

    boolean deleteById(MovementHistoryId id);
}
