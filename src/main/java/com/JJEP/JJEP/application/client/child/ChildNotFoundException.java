package com.JJEP.JJEP.application.client.child;

// custom exception to be thrown when a child is not found for more detailed error handling
public class ChildNotFoundException extends RuntimeException{
    // serial UID is used to verify that the sender and receiver of
    // a serialized object have loaded classes for that object that are compatible
    private static final long serialVersionUID = 4;

    public ChildNotFoundException(String message) {
        super(message);
    }
}
