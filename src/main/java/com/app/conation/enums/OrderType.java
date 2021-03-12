package com.app.conation.enums;

import lombok.Getter;

@Getter
public enum OrderType {
    NONE(""), VIEW("viewCount"), PRICE("price");

    private String field;

    OrderType(String field) {
        this.field = field;
    }
}
