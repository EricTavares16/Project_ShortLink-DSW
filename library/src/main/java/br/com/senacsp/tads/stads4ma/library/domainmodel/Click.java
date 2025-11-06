package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CLICK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id", nullable = false)
    private Link link; // FK para Link

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "cliked_id", nullable = false)
    private LocalDateTime clickedAt;

    @Column(name = "region", length = 100)
    private String region;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "device", length = 50)
    private String device;

    @Column(name = "referer", length = 255)
    private String referer;

}
