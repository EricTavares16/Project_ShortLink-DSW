package br.com.senacsp.tads.stads4ma.library.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDTO {

    private UUID id;
    private String type;
    private BigDecimal price;
    private Integer maxLinks;
}