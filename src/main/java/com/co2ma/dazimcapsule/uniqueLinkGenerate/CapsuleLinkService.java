package com.co2ma.dazimcapsule.uniqueLinkGenerate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CapsuleLinkService {
    private final CapsuleLinkRepository capsuleLinkRepository;

    public void createUniqueLink(CapsuleLinkRequestDTO capsuleLinkRequestDTO) {
        CapsuleLink capsuleLink = new CapsuleLink().builder()
                .uniqueLink(capsuleLinkRequestDTO.getUniqueLink())
                .expirationDate(capsuleLinkRequestDTO.getExpirationDate())
                .build();
        capsuleLinkRepository.save(capsuleLink);
    }
}
