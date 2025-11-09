package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistory;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementHistoryRepository extends JpaRepository<MovementHistory, MovementHistoryId> {

    List<MovementHistory> findByLink_Id(java.util.UUID linkId);

    List<MovementHistory> findByAction(String action);
}
