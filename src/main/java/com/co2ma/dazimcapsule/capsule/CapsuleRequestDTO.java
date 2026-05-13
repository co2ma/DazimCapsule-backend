package com.co2ma.dazimcapsule.capsule;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CapsuleRequestDTO(
        String name,
        String email,
        String uniqueLink,
        String body,
        LocalDate expirationDate
) {
}
