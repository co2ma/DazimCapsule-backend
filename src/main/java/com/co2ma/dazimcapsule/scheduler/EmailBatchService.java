package com.co2ma.dazimcapsule.scheduler;

import com.co2ma.dazimcapsule.capsule.CapsuleEntry;
import com.co2ma.dazimcapsule.capsule.CapsuleEntryRepository;
import com.co2ma.dazimcapsule.capsule.CapsuleEntryService;
import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLink;
import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLinkRepository;
import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLinkService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailBatchService {

    private final CapsuleLinkService capsuleLinkService;
    private final CapsuleEntryService capsuleEntryService;
    private final EmailSendService emailSendService;

    public void run(int tmp) throws MessagingException {
        List<CapsuleLink> links = capsuleLinkService.findByExpirationDateAndIsDone(LocalDate.now().plusDays(tmp));
        for(CapsuleLink link : links) {
            log.info("Link: {}", link);
            List<CapsuleEntry> entries = capsuleEntryService.findByCapsuleLinkAndIsProcessed(link);

            emailSendService.emailProcess(entries);
            link.markAsDone();
            capsuleLinkService.saveTrue(link);
        }
    }
}
