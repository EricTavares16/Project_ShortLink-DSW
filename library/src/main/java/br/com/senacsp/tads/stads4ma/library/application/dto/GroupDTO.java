package br.com.senacsp.tads.stads4ma.library.application.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDTO {
    private UUID id;
    private String name;
    private LocalDate createdAt;
}
