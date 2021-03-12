package com.app.conation.domain.draw;

import lombok.Getter;

@Getter
public enum DayPrice {
    SUN(1, "베스킨라빈스", 0.3),
    MON(2, "비타500", 1.0),
    THU(3, "비타500", 1.0),
    WEN(4, "비타500", 1.0),
    TUR(5, "비타500", 1.0),
    FRI(6, "비타500", 1.0),
    SAT(7, "스타벅스 아메리카노", 0.3);

    private final int dayOfWeek;
    private final String prize;
    private final double winningRate;

    DayPrice(int dayOfWeek, String prize, double winningRate) {
        this.dayOfWeek = dayOfWeek;
        this.prize = prize;
        this.winningRate = winningRate;
    }
}
