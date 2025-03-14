package com.alessandro.chatApplication.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "User was not found.";
    }
}
