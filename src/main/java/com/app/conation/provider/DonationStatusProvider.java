package com.app.conation.provider;

import com.app.conation.domain.DonationStatus;
import com.app.conation.domain.DonationStatusRepository;
import com.app.conation.domain.Region;
import com.app.conation.exception.BaseException;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.response.GetDonationStatusRes;
import com.app.conation.response.RegionDonationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
public class DonationStatusProvider {

    private final DonationStatusRepository donationStatusRepository;
    private final RegionProvider regionProvider;

    @Autowired
    public DonationStatusProvider(DonationStatusRepository donationStatusRepository, RegionProvider regionProvider) {
        this.donationStatusRepository = donationStatusRepository;
        this.regionProvider = regionProvider;
    }

    public DonationStatus getDonationStatusByRegionId(Region region) {
        Optional<DonationStatus> donationStatus = donationStatusRepository.findDonationStatusByRegionId(region);
        if (donationStatus.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_DONATION_STATUS);
        }
        return donationStatus.get();
    }

    public GetDonationStatusRes retrieveDonationStatus() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<DonationStatus> donationStatuses = donationStatusRepository.findAll();
        Long untilYesterdayDonationScore = getUntilYesterdayDonationScore(donationStatuses);
        Long todayDonationScore = getTodayDonationScore(donationStatuses);
        Long totalDonationScore = untilYesterdayDonationScore + todayDonationScore;
        List<RegionDonationStatus> regionDonationStatuses = getRegionDonationStatues(donationStatuses);
        return new GetDonationStatusRes(totalDonationScore, today, todayDonationScore, regionDonationStatuses);
    }

    private List<RegionDonationStatus> getRegionDonationStatues(List<DonationStatus> donationStatuses) {
        List<RegionDonationStatus> regionDonationStatuses = donationStatuses.stream()
                .map(DonationStatus::getRegionId)
                .sorted(comparing(Region::getDonationScore).reversed())
                .map(region -> new RegionDonationStatus(region.getRegionName(), region.getDonationScore()))
                .collect(toList());
        return regionDonationStatuses;
    }

    private Long getUntilYesterdayDonationScore(List<DonationStatus> donationStatuses) {
        Long untilYesterdayTotalDonationScore = donationStatuses.stream()
                .map(DonationStatus::getRegionId)
                .mapToLong(Region::getDonationScore)
                .sum();
        return untilYesterdayTotalDonationScore;
    }

    private Long getTodayDonationScore(List<DonationStatus> donationStatuses) {
        Long todayDonationScore = donationStatuses.stream()
                .map(donationStatus -> donationStatus.getTodayDonationScore())
                .mapToLong(Long::longValue)
                .sum();
        return todayDonationScore;
    }
}
