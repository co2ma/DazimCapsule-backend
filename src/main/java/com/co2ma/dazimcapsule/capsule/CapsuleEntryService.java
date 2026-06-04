package com.co2ma.dazimcapsule.capsule;

import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLink;
import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CapsuleEntryService {
    private final CapsuleEntryRepository capsuleEntryRepository;
    private final CapsuleLinkService capsuleLinkService;

    public void createCapsule(CapsuleEntryRequestDTO capsuleEntryRequestDTO) {
        Optional<CapsuleLink> link = capsuleLinkService.findByUniqueLink(capsuleEntryRequestDTO.getUniqueLink());
        System.out.println("link 테스트: " + link.get());
        String name = capsuleEntryRequestDTO.getName();
        String body = capsuleEntryRequestDTO.getBody();
        String email = capsuleEntryRequestDTO.getEmail();
        CapsuleEntry capsuleEntry = CapsuleEntry.builder()
                .name(name)
                .body(body)
                .email(email)
                .capsuleLink(link.get())
                .build();
        capsuleEntryRepository.save(capsuleEntry);
    }

    public boolean verifyCapsuleLink(String link) {
        return capsuleLinkService.findByUniqueLink(link).isPresent()?true:false;
    }
}
