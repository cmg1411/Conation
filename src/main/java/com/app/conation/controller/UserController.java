package com.app.conation.controller;

import com.app.conation.dto.SignInRequestDto;
import com.app.conation.dto.SignUpRequestDto;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"회원관리"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public BaseResponse<Object> signUp(@RequestBody @Validated SignUpRequestDto signUpRequestDto) {
        userService.userSignUp(signUpRequestDto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/signIn")
    public BaseResponse<String> signIn(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        String jwt = userService.userSignIn(signInRequestDto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, jwt);
    }
}
