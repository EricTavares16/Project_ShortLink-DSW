package br.com.senacsp.tads.stads4ma.library.application.dto;

import br.com.senacsp.tads.stads4ma.library.domainmodel.RoleType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    private UUID id;
    private RoleType type;
}
