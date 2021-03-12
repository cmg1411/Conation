package com.app.conation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GetDonationStatusRes {

    private Long totalDonationScore;
    private String today;
    private Long todayDonationScore;
    private List<RegionDonationStatus> regionDonationStatuses;
}
