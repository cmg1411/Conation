package com.app.conation.util;

public class StringUtility {

    private StringUtility() {}

    public static String subHyphen(String phone) {
        return phone.replaceAll("\\D", "");
    }
}
