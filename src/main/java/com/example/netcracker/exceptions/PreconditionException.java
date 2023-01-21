package com.example.netcracker.exceptions;

/*for status code 412*/
public class PreconditionException extends RuntimeException {
    public PreconditionException() {
    }

    public PreconditionException(String message) {
        super(message);
    }

    public PreconditionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PreconditionException(Throwable cause) {
        super(cause);
    }

    public PreconditionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
