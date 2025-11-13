package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "GROUP_MEMBER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMember {

    @EmbeddedId
    private GroupMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupId")
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "role", nullable = false, length = 20)
    private String role; // agora é só um texto, ex: "ADMIN" ou "USER"

    @Column(name = "added_at", nullable = false)
    private LocalDate addedAt;

    public void setRole(String role) {
        this.role = role;
    }
}
