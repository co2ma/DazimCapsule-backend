package com.co2ma.dazimcapsule.capsule;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CapsuleResponseDTO(
        String name,
        String email,
        String uniqueLink,
        String body,
        LocalDate expirationDate,
        boolean isProcessed
) {
}
