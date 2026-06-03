package com.co2ma.dazimcapsule.uniqueLinkGenerate;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapsuleLinkRequestDTO {
    private String uniqueLink;
    private LocalDate expirationDate;
}
