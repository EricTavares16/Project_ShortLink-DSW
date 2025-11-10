package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class MovementHistoryId implements Serializable {

    // 1. Chave Estrangeira do Link
    @Column(name = "link_id")
    private UUID linkId;

    // 2. Componente de Data (Mantido para consultas por dia)
    @Column(name = "movement_date")
    private LocalDate movementDate;

    // 3. NOVO COMPONENTE DE UNICIDADE (PK): Garante que múltiplos eventos no mesmo dia sejam únicos
    @Column(name = "sequence_id")
    private UUID sequenceId;
}