package com.app.conation.request;

import com.app.conation.enums.AdvertisementCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class PatchAdvertisementReq {

    @NotNull(message = "광고 번호를 입력해주세요.")
    private Long advertisementId;

    @NotBlank(message = "광고 이름을 입력해주세요.")
    private String advertisementName;

    @NotBlank(message = "광고 URL을 입력해주세요.")
    private String url;

    @NotNull(message = "광고 시간(초 단위)을 입력해주세요.")
    private Long length;

    @NotNull(message = "광고 단가를 입력해주세요.")
    private Long price;

    @NotNull(message = "광고주 번호를 입력해주세요.")
    private Long advertisementOwnerId;

    @NotNull(message = "광고 카테고리를 입력해주세요.")
    private AdvertisementCategory advertisementCategory;
}
