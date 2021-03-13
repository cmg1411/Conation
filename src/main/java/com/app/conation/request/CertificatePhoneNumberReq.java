package com.app.conation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class CertificatePhoneNumberReq {

    @NotBlank
    private String phoneNumber;
}
