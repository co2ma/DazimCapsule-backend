package com.co2ma.dazimcapsule.capsule;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CapsuleService {
    private final CapsuleRepository capsuleRepository;

    public CapsuleService(CapsuleRepository capsuleRepository) {
        this.capsuleRepository = capsuleRepository;
    }

    public void createCapsule(CapsuleDTO capsuleDTO) {
        String name = capsuleDTO.name();
        String body = capsuleDTO.body();
        String email = capsuleDTO.email();
        LocalDate expirationDate = capsuleDTO.expirationDate();
        Capsule capsule = new Capsule().builder()
                .name(name)
                .body(body)
                .email(email)
                .expirationDate(expirationDate)
                .build();
        capsuleRepository.save(capsule);
    }
}
