package com.app.conation.service;

import com.app.conation.domain.*;
import com.app.conation.provider.AdvertisementProvider;
import com.app.conation.request.PatchAdvertisementReq;
import com.app.conation.request.PostAdvertisementReq;
import com.app.conation.request.ViewAdvertisementReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.conation.config.Constant.DEFAULT_VIEW_COUNT;
import static com.app.conation.config.Constant.ONE;

@Transactional
@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementProvider advertisementProvider;
    private final DonationStatusService donationStatusService;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository, AdvertisementProvider advertisementProvider, DonationStatusService donationStatusService) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementProvider = advertisementProvider;
        this.donationStatusService = donationStatusService;
    }

    public Long createAdvertisement(PostAdvertisementReq request) {
        Advertisement advertisement = Advertisement.builder()
                .advertisementName(request.getAdvertisementName())
                .url(request.getUrl())
                .length(request.getLength())
                .price(request.getPrice())
                .advertisementOwnerId(request.getAdvertisementOwnerId())
                .advertisementCategory(request.getAdvertisementCategory())
                .state(State.ACTIVE)
                .viewCount(DEFAULT_VIEW_COUNT)
                .build();
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return savedAdvertisement.getId();
    }

    public Long updateAdvertisement(PatchAdvertisementReq request) {
        Long advertisementId = request.getAdvertisementId();
        Advertisement advertisement = advertisementProvider.getAdvertisementById(advertisementId);
        advertisement.setAdvertisementCategory(request.getAdvertisementCategory())
                .setAdvertisementName(request.getAdvertisementName())
                .setAdvertisementOwnerId(request.getAdvertisementOwnerId())
                .setLength(request.getLength())
                .setPrice(request.getPrice())
                .setUrl(request.getUrl());
        advertisementRepository.save(advertisement);
        return request.getAdvertisementId();
    }

    public Long deleteAdvertisement(Long advertisementId) {
        Advertisement advertisement = advertisementProvider.getAdvertisementById(advertisementId);
        advertisement.setState(State.INACTIVE);
        return advertisementId;
    }

    public Long viewAdvertisement(ViewAdvertisementReq viewAdvertisementRequest) {
        Advertisement advertisement = advertisementProvider
                .getAdvertisementById(viewAdvertisementRequest.getAdvertisementId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Region region = ((User) authentication.getPrincipal()).getRegionId();

        donationStatusService.addTodayDonationScore(region, advertisement.getPrice());
        advertisement.setViewCount(advertisement.getViewCount() + ONE);
        advertisementRepository.save(advertisement);

        return viewAdvertisementRequest.getAdvertisementId();
    }
}
