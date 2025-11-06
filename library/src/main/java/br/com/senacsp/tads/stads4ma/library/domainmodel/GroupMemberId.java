package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GroupMemberId implements Serializable {

    private UUID userId;
    private UUID groupId;
}
