package com.app.conation.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "nickname", "password", "passwordRepeat", "cityId", "phoneNumber"})
public class SignUpRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeat;

    @Positive
    private Long cityId;

    @NotBlank
    private String phoneNumber;
}
