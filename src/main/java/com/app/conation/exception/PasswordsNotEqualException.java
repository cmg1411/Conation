package com.app.conation.exception;

public class PasswordsNotEqualException extends RuntimeException {

    public PasswordsNotEqualException() {
        super();
    }

    public PasswordsNotEqualException(String message) {
        super(message);
    }

    public PasswordsNotEqualException(String message, Throwable cause) {
        super(message, cause);
    }
}
