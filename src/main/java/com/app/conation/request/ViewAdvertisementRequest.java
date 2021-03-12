package com.app.conation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class ViewAdvertisementRequest {

    @NotNull(message = "광고 번호를 입력해주세요.")
    private Long advertisementId;
}
