package com.app.conation.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyScoreRes {

    private Long id;
    private String nickname;
    private String myRegion;
    private Long point;
    private String todayPrize;
    private double prizeWinRate;
}
