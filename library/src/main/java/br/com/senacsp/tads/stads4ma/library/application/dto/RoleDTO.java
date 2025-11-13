package br.com.senacsp.tads.stads4ma.library.application.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    private UUID id;
    private String type;
}
