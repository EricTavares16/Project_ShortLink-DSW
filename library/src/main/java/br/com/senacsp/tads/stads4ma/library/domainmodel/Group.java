package br.com.senacsp.tads.stads4ma.library.domainmodel;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "GROUPS")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "created_at")
    private LocalDate createdAt;


    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Link> links;


    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupMember> members;
}
