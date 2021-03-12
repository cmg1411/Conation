package com.app.conation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeat;

    @NotBlank
    private Long cityId;

    @NotBlank
    private String phoneNumber;
}
