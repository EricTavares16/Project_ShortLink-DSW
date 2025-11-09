package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Click;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClickService {

    List<Click> findAll();

    Optional<Click> findById(UUID id);

    List<Click> findByLink(UUID linkId);

    Click save(Click click);

    boolean delete(UUID id);
}
