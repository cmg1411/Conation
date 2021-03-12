package com.app.conation.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
        super();
    }

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
