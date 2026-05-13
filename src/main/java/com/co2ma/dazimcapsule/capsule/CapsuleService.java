package com.co2ma.dazimcapsule.capsule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CapsuleService {
    private final CapsuleRepository capsuleRepository;

    public CapsuleResponseDTO createCapsule(CapsuleRequestDTO request){
        Capsule capsule = Capsule.builder()
                .name(request.name())
                .email(request.email())
                .body(request.body())
                .expirationDate(request.expirationDate())
                .uniqueLink(request.uniqueLink())
                .isProcessed(false)
                .build();

        Capsule saved = capsuleRepository.save(capsule);

        return CapsuleResponseDTO.builder()
                .name(saved.getName())
                .email(saved.getEmail())
                .uniqueLink(saved.getUniqueLink())
                .expirationDate(saved.getExpirationDate())
                .build();
    }
}
