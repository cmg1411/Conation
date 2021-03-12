package com.app.conation.enums;

import lombok.Getter;

@Getter
public enum OrderType {
    NONE(""), VIEW("viewCount"), SCORE("score");

    private String field;

    OrderType(String field) {
        this.field = field;
    }
}
