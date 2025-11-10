package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.GroupDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Group;

import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.GroupRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> findAll() {
        List<Group> resultset = new ArrayList<>();
        resultset.addAll(this.groupRepository.findAll());
        return resultset;
    }

    @Override
    public Group findById(UUID id) {
        return this.groupRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!groupRepository.existsById(id)) return false;
        groupRepository.deleteById(id);
        return true;
    }

    @Override
    public Group update(UUID id, Group group) {
        if (!groupRepository.existsById(id)) return null;
        group.setId(id);
        return groupRepository.save(group);
    }

    @Override
    public Group create(Group group) {
        return (Group) this.groupRepository.save(group);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.groupRepository.existsById(id);
    }

    private GroupDTO toDTO(Group group) {
        if (group == null) return null;

        return GroupDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .createdAt(group.getCreatedAt())
                .build();
    }

    private Group toEntity(GroupDTO dto) {
        if (dto == null) return null;

        Group group = new Group();
        group.setId(dto.getId());
        group.setName(dto.getName());
        group.setCreatedAt(dto.getCreatedAt());

        return group;
    }

    @Override
    public List<GroupDTO> findAllDTO() {
        return groupRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTO findDTOById(UUID id) {
        Group group = groupRepository.findById(id).orElse(null);
        return toDTO(group);
    }

    @Override
    public GroupDTO createDTO(GroupDTO groupDTO) {
        Group group = toEntity(groupDTO);
        Group saved = groupRepository.save(group);
        return toDTO(saved);
    }

    @Override
    public GroupDTO updateDTO(UUID id, GroupDTO groupDTO) {
        if (!groupRepository.existsById(id)) return null;
        Group group = toEntity(groupDTO);
        group.setId(id);
        Group updated = groupRepository.save(group);
        return toDTO(updated);
    }

}
