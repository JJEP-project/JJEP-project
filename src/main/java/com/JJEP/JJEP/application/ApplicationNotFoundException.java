package com.JJEP.JJEP.application;

public class ApplicationNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3;

    public ApplicationNotFoundException(String message) {
        super(message);

    }
}
