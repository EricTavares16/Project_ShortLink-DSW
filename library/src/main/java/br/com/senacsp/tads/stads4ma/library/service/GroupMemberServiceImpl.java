package br.com.senacsp.tads.stads4ma.library.service;
import br.com.senacsp.tads.stads4ma.library.application.dto.GroupMemberDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.*;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.GroupMemberRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.GroupRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.RoleRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public GroupMemberDTO create(GroupMemberDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));
        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Função não encontrada"));

        GroupMemberId id = new GroupMemberId(dto.getUserId(), dto.getGroupId());
        GroupMember member = GroupMember.builder()
                .id(id)
                .user(user)
                .group(group)
                .role("USER")
                .addedAt(dto.getAddedAt() != null ? dto.getAddedAt() : LocalDate.now())
                .build();

        GroupMember saved = groupMemberRepository.save(member);
        return GroupMemberDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public void delete(UUID userId, UUID groupId) {
        GroupMemberId id = new GroupMemberId(userId, groupId);
        if (!groupMemberRepository.existsById(id)) {
            throw new EntityNotFoundException("Associação não encontrada");
        }
        groupMemberRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupMemberDTO findById(UUID userId, UUID groupId) {
        GroupMemberId id = new GroupMemberId(userId, groupId);
        GroupMember member = groupMemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        return GroupMemberDTO.fromEntity(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupMemberDTO> findAll() {
        return groupMemberRepository.findAll().stream()
                .map(GroupMemberDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public GroupMemberDTO addMember(UUID groupId, UUID userId, UUID roleId) {
        return null;
    }

    @Override
    public void removeMember(UUID groupId, UUID userId) {

    }

    @Override
    public List<GroupMemberDTO> listMembersByGroup(UUID groupId) {
        return List.of();
    }

    @Override
    public List<GroupMemberDTO> listGroupsByUser(UUID userId) {
        return List.of();
    }
}