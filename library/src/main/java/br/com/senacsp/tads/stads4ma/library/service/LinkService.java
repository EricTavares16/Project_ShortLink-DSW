package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.LinkDTO;
import br.com.senacsp.tads.stads4ma.library.models.LinkRequest;

import java.util.List;
import java.util.UUID;

public interface LinkService {
    LinkDTO createLink(LinkRequest request, UUID userId);
    List<LinkDTO> getLinksByUser(UUID userId);
    LinkDTO getLinkById(UUID linkId);
    void deleteLink(UUID linkId);
}
