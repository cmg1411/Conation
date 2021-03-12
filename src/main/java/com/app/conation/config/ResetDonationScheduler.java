package com.app.conation.config;

import com.app.conation.service.DonationStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ResetDonationScheduler {

    private final DonationStatusService donationStatusService;

    @Scheduled(cron = "0 0/10 * * * *")
    public void resetDonationScoreEveryDay() {
        donationStatusService.resetDonationScore();
    }
}
