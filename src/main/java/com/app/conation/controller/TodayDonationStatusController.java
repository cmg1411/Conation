package com.app.conation.controller;

import com.app.conation.provider.TodayDonationStatusProvider;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.response.GetDonationStatusRes;
import com.app.conation.service.DonationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodayDonationStatusController {

    private final TodayDonationStatusProvider donationStatusProvider;
    private final DonationStatusService donationStatusService;

    @Autowired
    public TodayDonationStatusController(TodayDonationStatusProvider donationStatusProvider, DonationStatusService donationStatusService) {
        this.donationStatusProvider = donationStatusProvider;
        this.donationStatusService = donationStatusService;
    }

    @GetMapping("/donation-status")
    public BaseResponse<GetDonationStatusRes> getDonationStatus() {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, donationStatusProvider.retrieveDonationStatus());
    }
}
