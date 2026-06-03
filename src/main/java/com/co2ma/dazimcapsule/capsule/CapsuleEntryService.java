package com.co2ma.dazimcapsule.capsule;

import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLink;
import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CapsuleEntryService {
    private final CapsuleEntryRepository capsuleEntryRepository;
    private final CapsuleLinkRepository capsuleLinkRepository;

    public void createCapsule(CapsuleEntryRequestDTO capsuleEntryRequestDTO) {
        if(capsuleEntryRepository.existsByEmailAndCapsuleLink(capsuleEntryRequestDTO.getEmail(), capsuleEntryRequestDTO.getUniqueLink())) {
            throw new IllegalArgumentException("이미 입력 된 이메일 입니다.");
        }
        CapsuleLink link = capsuleLinkRepository.findByUniqueLink(capsuleEntryRequestDTO.getUniqueLink());
        String name = capsuleEntryRequestDTO.getName();
        String body = capsuleEntryRequestDTO.getBody();
        String email = capsuleEntryRequestDTO.getEmail();
        CapsuleEntry capsuleEntry = new CapsuleEntry().builder()
                .name(name)
                .body(body)
                .email(email)
                .capsuleLink(link)
                .build();
        capsuleEntryRepository.save(capsuleEntry);
    }
}
