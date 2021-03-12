package com.app.conation.provider;

import com.app.conation.domain.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class AdvertisementProvider {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementProvider(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }
}
