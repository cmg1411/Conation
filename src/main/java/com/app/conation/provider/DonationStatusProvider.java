package com.app.conation.provider;

import com.app.conation.domain.DonationStatus;
import com.app.conation.domain.DonationStatusRepository;
import com.app.conation.domain.Region;
import com.app.conation.exception.BaseException;
import com.app.conation.response.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class DonationStatusProvider {

    private final DonationStatusRepository donationStatusRepository;

    @Autowired
    public DonationStatusProvider(DonationStatusRepository donationStatusRepository) {
        this.donationStatusRepository = donationStatusRepository;
    }

    public DonationStatus getDonationStatusByRegionId(Region region) {
        Optional<DonationStatus> donationStatus = donationStatusRepository.findDonationStatusByRegionId(region);
        if (donationStatus.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_DONATION_STATUS);
        }
        return donationStatus.get();
    }
}
