package com.co2ma.dazimcapsule.uniqueLinkGenerate;

import com.co2ma.dazimcapsule.capsule.CapsuleEntry;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CapsuleLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueLink;

    private LocalDate expirationDate;

    @OneToMany(mappedBy = "capsuleLink", cascade = CascadeType.ALL)
    private List<CapsuleEntry> capsuleEntries = new ArrayList<>();

    @Builder
    public CapsuleLink(String uniqueLink, LocalDate expirationDate) {
        this.uniqueLink = uniqueLink;
        this.expirationDate = expirationDate;
    }
}
