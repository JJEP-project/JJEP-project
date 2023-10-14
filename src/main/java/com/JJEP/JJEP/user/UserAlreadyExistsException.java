package com.JJEP.JJEP.user;

public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVerisionUID = 2;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}