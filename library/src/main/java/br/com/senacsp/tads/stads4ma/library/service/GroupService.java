package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.GroupDTO;
import org.springframework.transaction.annotation.Transactional;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Group;

import java.util.List;
import java.util.UUID;

@Transactional

public interface GroupService {
    public List<Group> findAll();

    public Group findById(UUID id);

    public boolean deleteById(UUID id);

    public Group create(Group group);

    boolean existsById(UUID id);

    Group update(UUID id, Group databaseGroup);

    List<GroupDTO> findAllDTO();

    GroupDTO findDTOById(UUID id);

    GroupDTO createDTO(GroupDTO groupDTO);

    GroupDTO updateDTO(UUID id, GroupDTO groupDTO);

}
