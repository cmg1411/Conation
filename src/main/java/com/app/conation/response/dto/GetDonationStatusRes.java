package com.app.conation.response.dto;

import com.app.conation.response.RegionDonationStatus;
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
