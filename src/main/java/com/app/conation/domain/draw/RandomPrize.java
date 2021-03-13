package com.app.conation.domain.draw;

import com.app.conation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomPrize {

    enum WinningStatus {
        WIN("당첨 !!"), NO("다음 기회에..");

        private final String status;

        WinningStatus(String status) {
            this.status = status;
        }
    }

    @Autowired
    private MessageSender messageSender;

    private final DayPrice dayPrice;

    public RandomPrize() {
        this.dayPrice = getDayPrice();
    }

    public static DayPrice getDayPrice() {
        Calendar calendar = Calendar.getInstance();
        return Arrays.stream(DayPrice.values())
            .filter(day -> day.getDayOfWeek() == calendar.get(Calendar.DAY_OF_WEEK))
            .findAny()
            .orElseGet(() -> DayPrice.MON);
    }

    public String draw(User user) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int drawNum = random.nextInt(1000) + 1;
        if (isWinner(drawNum)) {
            sendMessage(user);
            return WinningStatus.WIN.status;
        }
        return WinningStatus.NO.status;
    }

    private boolean isWinner(int drawNum) {
        double WinnerNum = 1000 * dayPrice.getWinningRate() / 100;
        return drawNum <= WinnerNum;
    }

    private void sendMessage(User user) {
        UserMessageParameters userMessageParameters = messageSender.userInformationSetting(user, getDayPrice());
        messageSender.sendSMS(userMessageParameters);
    }
}
