package com.app.conation.exception;

public class NotExistUserException extends RuntimeException {

    public NotExistUserException() {
        super();
    }

    public NotExistUserException(String message) {
        super(message);
    }

    public NotExistUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
