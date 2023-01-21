package com.example.netcracker.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorResponse {

    @JsonIgnore
    private Throwable exception;
    private String code;
    private String message;

    public ErrorResponse(Throwable exception) {
        this.exception = exception;
    }

    public Throwable getException() {
        return exception;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
