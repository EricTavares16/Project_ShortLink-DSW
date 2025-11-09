package br.com.senacsp.tads.stads4ma.library.application.dto;

import br.com.senacsp.tads.stads4ma.library.domainmodel.PlanType;
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
    private PlanType type;
    private BigDecimal price;
    private Integer maxLinks;
}