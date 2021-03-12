package com.app.conation.service;

import com.app.conation.domain.Advertisement;
import com.app.conation.domain.AdvertisementRepository;
import com.app.conation.domain.State;
import com.app.conation.exception.BaseException;
import com.app.conation.request.PatchAdvertisementReq;
import com.app.conation.request.PostAdvertisementReq;
import com.app.conation.request.ViewAdvertisementRequest;
import com.app.conation.response.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.app.conation.config.Constant.DEFAULT_VIEW_COUNT;
import static com.app.conation.config.Constant.ONE;

@Transactional
@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
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
        Advertisement advertisement = getAdvertisementById(advertisementId);
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
        Advertisement advertisement = getAdvertisementById(advertisementId);
        advertisement.setState(State.INACTIVE);
        return advertisementId;
    }

    private Advertisement getAdvertisementById(Long advertisementId) {
        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(advertisementId);
        if (optionalAdvertisement.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ADVERTISEMENT);
        }
        return optionalAdvertisement.get();
    }

    public Long viewAdvertisement(ViewAdvertisementRequest viewAdvertisementRequest) {
        Advertisement advertisement = getAdvertisementById(viewAdvertisementRequest.getAdvertisementId());
        advertisement.setViewCount(advertisement.getViewCount() + ONE);
        advertisementRepository.save(advertisement);
        return viewAdvertisementRequest.getAdvertisementId();
    }
}
