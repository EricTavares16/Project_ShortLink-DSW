package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "GROUP_MEMBER")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GroupMember {

    @EmbeddedId
    private GroupMemberId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne // Agora é entidade, não Enum
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "added_at")
    private LocalDate addedAt;
}
