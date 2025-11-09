package br.com.senacsp.tads.stads4ma.library.application.dto;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClickDTO {

    private UUID id;
    private UUID linkId;
    private LocalDate date;
    private LocalDateTime clickedAt;
    private String region;
    private String city;
    private String device;
    private String referer;
}
