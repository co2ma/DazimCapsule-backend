package com.co2ma.dazimcapsule.capsule;

import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLink;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CapsuleEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id")
    private CapsuleLink capsuleLink;

    private String name;

    @Column(unique = true)
    private String email;

    private String body;

    private boolean isProcessed = false;

    @Builder
    public CapsuleEntry(String name,
                        String email,
                        String body,
                        CapsuleLink capsuleLink) {
        this.name = name;
        this.email = email;
        this.body = body;
        this.capsuleLink = capsuleLink;
        this.isProcessed = false;
    }
}
