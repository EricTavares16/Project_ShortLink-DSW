package br.com.senacsp.tads.stads4ma.library.application.dto;

import br.com.senacsp.tads.stads4ma.library.domainmodel.MovementHistory;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementHistoryDTO {

    private UUID linkId;
    private LocalDate movementDate;
    private LocalDateTime createdAt;
    private Integer totalClicks;
    private String action;
    private String description;
    private String userName;

    public static MovementHistoryDTO fromEntity(MovementHistory entity) {
        return MovementHistoryDTO.builder()
                .linkId(entity.getLink().getId())
                .movementDate(entity.getId().getMovementDate())
                .createdAt(entity.getCreatedAt())
                .totalClicks(entity.getTotalClicks())
                .action(entity.getAction())
                .description(entity.getDescription())
                .userName(entity.getUserName())
                .build();
    }
}