package com.co2ma.dazimcapsule.capsule;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CapsuleRequestDTO {
    @NotBlank @NotNull String name;
    @Email @NotBlank @NotNull String email;
    @NotBlank @NotNull String body;
    String Link;
    LocalDate expirationDate;
}
