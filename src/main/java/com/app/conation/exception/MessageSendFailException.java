package com.app.conation.exception;

public class MessageSendFailException extends RuntimeException {

    public MessageSendFailException() {
        super();
    }

    public MessageSendFailException(String message) {
        super(message);
    }

    public MessageSendFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
