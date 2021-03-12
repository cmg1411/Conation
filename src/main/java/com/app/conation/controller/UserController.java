package com.app.conation.controller;

import com.app.conation.dto.SignUpRequestDto;
import com.app.conation.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"회원관리"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public void SignUp(@RequestBody @Validated SignUpRequestDto signUpRequestDto) {
        userService.userSignUp(signUpRequestDto);
    }

    @PostMapping()
    public void SignIn() {

    }
}
