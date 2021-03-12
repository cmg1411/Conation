package com.app.conation.controller;

import com.app.conation.enums.AdvertisementCategory;
import com.app.conation.provider.AdvertisementProvider;
import com.app.conation.request.PatchAdvertisementReq;
import com.app.conation.request.PostAdvertisementReq;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.response.GetAdvertisementRes;
import com.app.conation.service.AdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "광고 관리")
@RestController
public class AdvertisementController {

    private final AdvertisementProvider advertisementProvider;
    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementProvider advertisementProvider, AdvertisementService advertisementService) {
        this.advertisementProvider = advertisementProvider;
        this.advertisementService = advertisementService;
    }

    @ApiOperation(value = "광고 목록 조회")
    @GetMapping("/advertisements")
    public BaseResponse<List<GetAdvertisementRes>> getAdvertisements(@RequestParam Integer page,
                                                                     @RequestParam(required = false) AdvertisementCategory category) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementProvider.retrieveAdvertisements(pageable, category));
    }

    @ApiOperation(value = "광고 생성")
    @PostMapping("/advertisements")
    public BaseResponse<Long> postAdvertisement(@RequestBody @Valid PostAdvertisementReq request) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementService.createAdvertisement(request));
    }

    @ApiOperation(value = "광고 수정")
    @PatchMapping("/advertisements")
    public BaseResponse<Long> updateAdvertisement(@RequestBody @Valid PatchAdvertisementReq request) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementService.updateAdvertisement(request));
    }
}
