package com.co2ma.dazimcapsule.uniqueLinkGenerate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CapsuleLinkService {
    private final CapsuleLinkRepository capsuleLinkRepository;

    public void createUniqueLink(CapsuleLinkRequestDTO capsuleLinkRequestDTO) {
        CapsuleLink capsuleLink = CapsuleLink.builder()
                .uniqueLink(capsuleLinkRequestDTO.getUniqueLink())
                .expirationDate(capsuleLinkRequestDTO.getExpirationDate())
                .build();
        capsuleLinkRepository.save(capsuleLink);
    }

    public Optional<CapsuleLink> findByUniqueLink(String uniqueLink) {
        return capsuleLinkRepository.findByUniqueLink(uniqueLink);
    }

    public List<CapsuleLink> findByExpirationDateAndIsDone(LocalDate expirationDate) {
        return capsuleLinkRepository.findByExpirationDateAndIsDone(expirationDate, false);
    }

    public void saveTrue(CapsuleLink capsuleLink) {
        capsuleLinkRepository.save(capsuleLink);
    }

}
