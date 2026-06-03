package com.co2ma.dazimcapsule.capsule;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CapsuleEntryRequestDTO {
    @NotBlank String name;
    @Email @NotBlank String email;
    @NotBlank String body;
    String uniqueLink;
}
