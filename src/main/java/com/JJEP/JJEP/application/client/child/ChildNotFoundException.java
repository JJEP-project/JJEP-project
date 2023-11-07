package com.JJEP.JJEP.application.client.child;

public class ChildNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 4;

    public ChildNotFoundException(String message) {
        super(message);
    }
}
