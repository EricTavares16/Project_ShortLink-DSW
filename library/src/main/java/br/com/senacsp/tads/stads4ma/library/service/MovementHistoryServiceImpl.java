package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistory;
import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistoryId;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.MovementHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovementHistoryServiceImpl implements MovementHistoryService {

    private final MovementHistoryRepository repository;

    public MovementHistoryServiceImpl(MovementHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MovementHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MovementHistory> findById(MovementHistoryId id) {
        return repository.findById(id);
    }

    @Override
    public List<MovementHistory> findByLink(UUID linkId) {
        return repository.findByLink_Id(linkId);
    }

    @Override
    public List<MovementHistory> findByAction(String action) {
        return repository.findByAction(action);
    }

    @Override
    public MovementHistory save(MovementHistory history) {
        return repository.save(history);
    }

    @Override
    public boolean deleteById(MovementHistoryId id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
