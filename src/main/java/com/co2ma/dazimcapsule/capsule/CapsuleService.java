package com.co2ma.dazimcapsule.capsule;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CapsuleService {
    private final CapsuleRepository capsuleRepository;

    public CapsuleService(CapsuleRepository capsuleRepository) {
        this.capsuleRepository = capsuleRepository;
    }

    public void createCapsule(CapsuleRequestDTO capsuleRequestDTO) {
        String name = capsuleRequestDTO.getName();
        String body = capsuleRequestDTO.getBody();
        String email = capsuleRequestDTO.getEmail();
        String uniqueLink = capsuleRequestDTO.getUniqueLink();
        LocalDate expirationDate = capsuleRequestDTO.getExpirationDate();
        Capsule capsule = new Capsule().builder()
                .name(name)
                .body(body)
                .email(email)
                .uniqueLink(uniqueLink)
                .expirationDate(expirationDate)
                .build();
        capsuleRepository.save(capsule);
    }
}
