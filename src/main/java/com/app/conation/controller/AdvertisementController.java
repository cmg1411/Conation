package com.app.conation.controller;

import com.app.conation.provider.AdvertisementProvider;
import com.app.conation.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertisementController {

    private final AdvertisementProvider advertisementProvider;
    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementProvider advertisementProvider, AdvertisementService advertisementService) {
        this.advertisementProvider = advertisementProvider;
        this.advertisementService = advertisementService;
    }
}
