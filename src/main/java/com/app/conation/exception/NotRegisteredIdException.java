package com.app.conation.exception;

public class NotRegisteredIdException extends RuntimeException {

    public NotRegisteredIdException() {
        super();
    }

    public NotRegisteredIdException(String message) {
        super(message);
    }

    public NotRegisteredIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
