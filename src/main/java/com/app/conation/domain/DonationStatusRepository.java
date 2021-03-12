package com.app.conation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationStatusRepository extends JpaRepository<DonationStatus, Long> {

    Optional<DonationStatus> findDonationStatusByRegionId(Region regionId);
}
