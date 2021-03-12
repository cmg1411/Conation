package com.app.conation.controller;

import com.app.conation.provider.TodayDonationStatusProvider;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.response.GetDonationStatusRes;
import com.app.conation.service.DonationStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "기부 현황")
@RestController
public class TodayDonationStatusController {

    private final TodayDonationStatusProvider donationStatusProvider;

    @Autowired
    public TodayDonationStatusController(TodayDonationStatusProvider donationStatusProvider) {
        this.donationStatusProvider = donationStatusProvider;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/donation-status")
    public BaseResponse<GetDonationStatusRes> getDonationStatus() {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, donationStatusProvider.retrieveDonationStatus());
    }
}
