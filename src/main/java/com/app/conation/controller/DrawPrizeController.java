package com.app.conation.controller;

import com.app.conation.dto.DrawResponseDto;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.service.RandomDrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"랜덤뽑기"})
@RestController
@RequiredArgsConstructor
public class DrawPrizeController {

    private final RandomDrawService randomDrawService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/randomdraw")
    public BaseResponse<DrawResponseDto> randomDraw() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, randomDrawService.randomDraw(authentication.getPrincipal()));
    }
}
