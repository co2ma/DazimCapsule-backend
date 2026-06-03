package com.co2ma.dazimcapsule.capsule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CapsuleEntryRepository extends JpaRepository<CapsuleEntry,Long> {
    boolean existsByEmailAndCapsuleLink(String email, String uniqueLink);


}
