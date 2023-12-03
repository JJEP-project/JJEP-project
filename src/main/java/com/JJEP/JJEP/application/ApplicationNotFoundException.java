package com.JJEP.JJEP.application;

// custom exception thrown when an application is not found
public class ApplicationNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3;

    public ApplicationNotFoundException(String message) {
        super(message);

    }
}
