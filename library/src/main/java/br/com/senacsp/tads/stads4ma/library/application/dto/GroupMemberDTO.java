package br.com.senacsp.tads.stads4ma.library.application.dto;

import br.com.senacsp.tads.stads4ma.library.domainmodel.GroupMember;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMemberDTO {

    private UUID userId;
    private UUID groupId;
    private UUID roleId;
    private LocalDate addedAt;

    private String name;
    private String email;


    public static GroupMemberDTO fromEntity(GroupMember member) {
        GroupMemberDTO dto = new GroupMemberDTO();
        dto.setUserId(member.getUser().getId());
        dto.setGroupId(member.getGroup().getId());
        dto.setAddedAt(member.getAddedAt());
        dto.setName(member.getUser().getName());
        dto.setEmail(member.getUser().getEmail());
        return dto;
    }
}
