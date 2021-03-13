package com.app.conation.domain.draw;

import com.app.conation.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageSenderTest {

    private MessageSender messageSender = new MessageSender();

    @DisplayName("메세지 보내기 테스트")
    @Test
    void messageTest() {
        // given
        User user = User.builder()
            .id(1L)
            .nickname("tomas")
            .phoneNumber("01045692804")
            .password("123")
            .experiencePoint(10000L)
            .build();
        messageSender.userInformationSetting(user, DayPrice.FRI);

        // when
        messageSender.sendSMS();

        // then
    }
}