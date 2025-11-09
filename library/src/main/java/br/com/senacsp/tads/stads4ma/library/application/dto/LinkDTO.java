package br.com.senacsp.tads.stads4ma.library.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record LinkDTO(
        UUID id,
        String shortCode,
        String originalUrl,
        LocalDate createdAt,
        LocalDate expiresAt,
        boolean isActive,
        UUID userId,
        UUID groupId
) {}
