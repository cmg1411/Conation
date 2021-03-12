package com.app.conation.response;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    PLEASE_CHECK_REQUEST(false, 2000, "입력값을 확인해주세요."),
    NOT_FOUND_ADVERTISEMENT(false, 3000, "존재하지 않는 광고입니다."),
    NOT_FOUND_REGION(false, 3100, "존재하지 않는 지역입니다."),
    NOT_FOUND_DONATION_STATUS(false, 3200, "존재하지 않는 지역 기부 현황입니다.");

    private boolean isSuccess;
    private int code;
    private String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
