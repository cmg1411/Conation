package com.app.conation.controller;

import com.app.conation.request.SignInReq;
import com.app.conation.request.SignUpReq;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
    public BaseResponse<Object> signUp(@RequestBody @Validated SignUpReq signUpReq) {
        userService.userSignUp(signUpReq);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/signIn")
    public BaseResponse<String> signIn(@RequestBody @Valid SignInReq signInReq) {
        String jwt = userService.userSignIn(signInReq);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, jwt);
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
