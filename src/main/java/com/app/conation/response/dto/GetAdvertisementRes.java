package com.app.conation.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetAdvertisementRes {

    private Long advertisementId;
    private String advertisementName;
    private String url;
    private Long minutes;
    private Long seconds;
    private Long price;
    private Long viewCount;
}
