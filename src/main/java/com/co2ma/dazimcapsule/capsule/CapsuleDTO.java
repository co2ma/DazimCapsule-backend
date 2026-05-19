package com.co2ma.dazimcapsule.capsule;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CapsuleDTO(
    @NotBlank String name,
    @Email @NotBlank String email,
    @NotBlank String body,
    @NotNull LocalDate expirationDate
) {}
