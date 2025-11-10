package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "MOVEMENT_HISTORY")
@Getter @Setter
@NoArgsConstructor
public class MovementHistory {

    @EmbeddedId
    private MovementHistoryId id;

    // Mapeamento bidirecional da chave composta: usa o linkId do id como FK
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("linkId")
    @JoinColumn(name = "link_id")
    private Link link;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_clicks")
    private Integer totalClicks;

    @Column(name = "action", length = 50)
    private String action;

    @Column(name = "description")
    private String description;

    @Column(name = "user_name")
    private String userName;

    // Construtor @Builder para o ServiceImpl
    @Builder
    public MovementHistory(MovementHistoryId id, Link link, LocalDateTime createdAt, Integer totalClicks, String action, String description, String userName) {
        this.id = id;
        this.link = link;
        this.createdAt = createdAt;
        this.totalClicks = totalClicks;
        this.action = action;
        this.description = description;
        this.userName = userName;
    }
}