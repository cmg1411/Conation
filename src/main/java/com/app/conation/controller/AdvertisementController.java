package com.app.conation.controller;

import com.app.conation.enums.AdvertisementCategory;
import com.app.conation.enums.OrderType;
import com.app.conation.provider.AdvertisementProvider;
import com.app.conation.request.PatchAdvertisementReq;
import com.app.conation.request.PostAdvertisementReq;
import com.app.conation.request.ViewAdvertisementReq;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.response.GetAdvertisementRes;
import com.app.conation.service.AdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.app.conation.config.Constant.DEFAULT_PAGING_SIZE;
import static com.app.conation.config.Constant.ONE;

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
                                                                     @RequestParam(required = false) AdvertisementCategory category,
                                                                     @RequestParam OrderType orderType) {
        Pageable pageable = PageRequest.of(page - ONE, DEFAULT_PAGING_SIZE);
        if (!orderType.equals(OrderType.NONE)) {
            pageable = PageRequest.of(page - ONE, DEFAULT_PAGING_SIZE, Sort.by(orderType.getField()).descending());
        }
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementProvider.retrieveAdvertisements(pageable, category));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "광고 생성")
    @PostMapping("/advertisements")
    public BaseResponse<Long> postAdvertisement(@RequestBody @Valid PostAdvertisementReq request) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementService.createAdvertisement(request));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "광고 수정")
    @PatchMapping("/advertisements")
    public BaseResponse<Long> updateAdvertisement(@RequestBody @Valid PatchAdvertisementReq request) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementService.updateAdvertisement(request));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "광고 삭제")
    @DeleteMapping("/advertisements/{advertisementId}")
    public BaseResponse<Long> deleteAdvertisement(@PathVariable Long advertisementId) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementService.deleteAdvertisement(advertisementId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "광고 시청 완료시 조회수 증가")
    @PatchMapping("/view-advertisements")
    public BaseResponse<Long> viewAdvertisement(@RequestBody @Valid ViewAdvertisementReq viewAdvertisementRequest) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, advertisementService.viewAdvertisement(viewAdvertisementRequest));
    }
}
