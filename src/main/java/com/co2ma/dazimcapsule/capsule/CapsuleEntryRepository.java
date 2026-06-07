package com.co2ma.dazimcapsule.capsule;

import com.co2ma.dazimcapsule.uniqueLinkGenerate.CapsuleLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CapsuleEntryRepository extends JpaRepository<CapsuleEntry,Long> {
    List<CapsuleEntry> findByCapsuleLinkAndIsProcessed(CapsuleLink capsuleLink, boolean isProcessed);
}
