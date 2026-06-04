package com.co2ma.dazimcapsule.uniqueLinkGenerate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CapsuleLinkRepository extends JpaRepository<CapsuleLink, Long> {
    Optional<CapsuleLink> findByUniqueLink(String uniqueLink);
    List<LocalDate> findAllByExpirationDate(LocalDate expirationDate);
}
