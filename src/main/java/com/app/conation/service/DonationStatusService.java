package com.app.conation.service;

import com.app.conation.domain.DonationStatus;
import com.app.conation.domain.DonationStatusRepository;
import com.app.conation.domain.Region;
import com.app.conation.provider.DonationStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DonationStatusService {

    private final DonationStatusRepository donationStatusRepository;
    private final DonationStatusProvider donationStatusProvider;

    @Autowired
    public DonationStatusService(DonationStatusRepository donationStatusRepository, DonationStatusProvider donationStatusProvider) {
        this.donationStatusRepository = donationStatusRepository;
        this.donationStatusProvider = donationStatusProvider;
    }

    public Long addTodayDonationScore(Region region, Long donationScore) {
        DonationStatus donationStatus = donationStatusProvider.getDonationStatusByRegionId(region);
        Long todayDonationScore = donationStatus.getTodayDonationScore();
        donationStatus.setTodayDonationScore(todayDonationScore + donationScore);
        donationStatusRepository.save(donationStatus);
        return donationStatus.getId();
    }
}
