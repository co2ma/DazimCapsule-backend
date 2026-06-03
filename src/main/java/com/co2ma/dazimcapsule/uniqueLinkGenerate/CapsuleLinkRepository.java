package com.co2ma.dazimcapsule.uniqueLinkGenerate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapsuleLinkRepository extends JpaRepository<CapsuleLink, Long> {
    CapsuleLink findByUniqueLink(String uniqueLink);
}
