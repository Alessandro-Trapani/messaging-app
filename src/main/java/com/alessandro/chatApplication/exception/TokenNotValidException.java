package com.alessandro.chatApplication.exception;

public class TokenNotValidException extends RuntimeException {
    public TokenNotValidException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Token is not valid.";
    }
}
