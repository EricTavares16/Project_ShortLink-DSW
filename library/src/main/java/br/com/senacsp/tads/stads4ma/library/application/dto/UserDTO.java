package br.com.senacsp.tads.stads4ma.library.application.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String roleName;  // ex: "ADMIN" ou "USER"
    private String planName;  // ex: "FREE", "PREMIUM"
    private UUID profileId;   // id do Profile associado (se houver)

}