package com.app.conation.request;

import com.app.conation.enums.AdvertisementCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class PostAdvertisementReq {

    @NotBlank
    private String advertisementName;

    @NotBlank
    private String url;

    @NotNull
    @Positive
    private Long length;

    @NotNull
    @Positive
    private Long price;

    @NotNull
    @Positive
    private Long advertisementOwnerId;

    @NotNull
    private AdvertisementCategory advertisementCategory;
}
