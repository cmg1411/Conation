package com.app.conation.domain.draw;

import net.nurigo.java_sdk.Coolsms;

public class MessageSender {

    private static final String API_KEY = "NCSIZKOCGQT8ZIHO";
    private static final String API_SECRET_KEY = "JTD0T4SEUDTRQSHNPATERTRKFSTCK80Y";

    private Coolsms coolsms = new Coolsms(API_KEY, API_SECRET_KEY);
}
