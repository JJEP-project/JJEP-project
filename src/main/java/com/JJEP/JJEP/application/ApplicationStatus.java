package com.JJEP.JJEP.application;

// enum is used to represent a fixed number of possible values for the status of an application
public enum ApplicationStatus {
    REQUESTED(0),
    IN_PROGRESS(1),
    COMPLETED(2),
    CANCELLED(3);

    public final Integer status;

    private ApplicationStatus(Integer status) {
        this.status = status;
    }
}
