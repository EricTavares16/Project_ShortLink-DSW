package br.com.senacsp.tads.stads4ma.library.application.dto;


import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementHistoryDTO {
    private UUID linkId;
    private LocalDate date;
    private String action;
    private String description;
    private String userName;
}
