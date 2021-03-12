package com.app.conation.controller;

import com.app.conation.dto.MyScoreResponseDto;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"마이페이지"})
@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/mypage")
    public BaseResponse<MyScoreResponseDto> getMyInformation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, userService.getMyPrice(authentication.getPrincipal()));
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping("/deleteUser")
    public BaseResponse<Object> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(authentication.getPrincipal());
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
