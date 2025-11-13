package br.com.senacsp.tads.stads4ma.library.service;
import br.com.senacsp.tads.stads4ma.library.application.dto.GroupMemberDTO;

import java.util.List;
import java.util.UUID;

public interface GroupMemberService {


    GroupMemberDTO create(GroupMemberDTO dto);

    void delete(UUID userId, UUID groupId);

    GroupMemberDTO findById(UUID userId, UUID groupId);

    List<GroupMemberDTO> findAll();

    GroupMemberDTO addMember(UUID groupId, UUID userId, UUID roleId);

    void removeMember(UUID groupId, UUID userId);

    List<GroupMemberDTO> listMembersByGroup(UUID groupId);

    List<GroupMemberDTO> listGroupsByUser(UUID userId);
}
