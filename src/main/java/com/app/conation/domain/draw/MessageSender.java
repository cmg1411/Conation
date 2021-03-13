package com.app.conation.domain.draw;

import com.app.conation.domain.User;
import com.app.conation.exception.MessageSendFailException;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MessageSender {

    private static final String ADMIN_PHONE_NUMBER = "01045692804";
    private static final String API_KEY = "NCSIZKOCGQT8ZIHO";
    private static final String API_SECRET_KEY = "JTD0T4SEUDTRQSHNPATERTRKFSTCK80Y";

    private final Message coolSms = new Message(API_KEY, API_SECRET_KEY);

    public UserMessageParameters userInformationSetting(User user, DayPrice price) {
        UserMessageParameters params = new UserMessageParameters();
        params.setParam("to", user.getPhoneNumber());
        params.setParam("from", ADMIN_PHONE_NUMBER);
        params.setParam("type", "SMS");
        params.setParam("text", price.getPrize());
        params.setParam("app_version", "Conation v1.0");
        return params;
    }

    public UserMessageParameters userPhoneNumberSetting(String phoneNumber) {
        String randomNumber = generateRandomNumber();
        UserMessageParameters params = new UserMessageParameters();
        params.setParam("to", phoneNumber);
        params.setParam("from", ADMIN_PHONE_NUMBER);
        params.setParam("type", "SMS");
        params.setParam("text", randomNumber);
        params.setParam("app_version", "Conation v1.0");
        return params;
    }

    public String generateRandomNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int randomNumber = random.nextInt(10);
            stringBuilder.append(randomNumber);
        }
        return stringBuilder.toString();
    }

    public void sendSMS(UserMessageParameters userParams) {
        Map<String, String> params = userParams.getParameters();
        try {
            coolSms.send((HashMap<String, String>) params);
        } catch (CoolsmsException e) {
            throw new MessageSendFailException();
        }
    }
}
