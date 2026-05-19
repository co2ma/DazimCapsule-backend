package com.co2ma.dazimcapsule.capsule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Capsule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String body;
    private String uniqueLink;
    private LocalDate expirationDate;
    private boolean isProcessed = false;

    @Builder
    public Capsule(String name, String email, String body, String uniqueLink, LocalDate expirationDate) {
        this.name = name;
        this.email = email;
        this.body = body;
        this.uniqueLink = uniqueLink;
        this.expirationDate = expirationDate;
    }
}
