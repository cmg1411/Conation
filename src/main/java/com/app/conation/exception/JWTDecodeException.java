package com.app.conation.exception;

public class JWTDecodeException extends RuntimeException {

    public JWTDecodeException() {
        super();
    }

    public JWTDecodeException(String message) {
        super(message);
    }

    public JWTDecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
