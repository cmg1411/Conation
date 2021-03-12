package com.app.conation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyScoreResponseDto {

    private Long id;
    private String nickname;
    private Long point;
    private String todayPrize;
    private double prizeWinRate;
}
