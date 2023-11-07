package com.JJEP.JJEP.user;

public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 2;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}