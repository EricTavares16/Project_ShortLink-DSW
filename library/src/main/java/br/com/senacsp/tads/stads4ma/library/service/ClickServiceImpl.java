package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Click;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.ClickRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClickServiceImpl implements ClickService {

    private final ClickRepository repository;

    public ClickServiceImpl(ClickRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Click> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Click> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Click> findByLink(UUID linkId) {
        return repository.findByLink_Id(linkId);
    }

    @Override
    public Click save(Click click) {
        return repository.save(click);
    }

    @Override
    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
