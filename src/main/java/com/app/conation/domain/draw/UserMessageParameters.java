package com.app.conation.domain.draw;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserMessageParameters {

    private final Map<String, String> userParameters;

    public UserMessageParameters() {
        this.userParameters = new HashMap<>();
    }

    public void setParam(String key, String value) {
        userParameters.put(key, value);
    }

    public Map<String, String> getParameters() {
        return userParameters;
    }
}
