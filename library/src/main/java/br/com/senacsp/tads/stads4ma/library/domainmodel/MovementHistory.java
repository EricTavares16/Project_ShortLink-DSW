package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "MOVEMENT_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementHistory {

    @EmbeddedId
    private MovementHistoryId id;

    @MapsId("linkId") // Mapeia linkId da chave composta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id", nullable = false)
    private Link link;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_clicks", nullable = false)
    private Integer totalClicks;

    @Column(name = "action", length = 50, nullable = false)
    private String action; // Ex: CREATED, UPDATED, DELETED

    @Column(name = "description", length = 255)
    private String description; // Ex: "Usuário atualizou o link encurtado"

    @Column(name = "user_name", length = 100)
    private String userName; // Quem realizou a ação
}
