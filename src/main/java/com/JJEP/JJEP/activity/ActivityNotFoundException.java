package com.JJEP.JJEP.activity;

import java.io.Serial;

// custom exception to handle activity not found
public class ActivityNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4;

    public ActivityNotFoundException(String message) {
        super(message);

    }
}
