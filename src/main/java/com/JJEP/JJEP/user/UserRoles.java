package com.JJEP.JJEP.user;

public enum UserRoles {
    USER("user"),
    ADMIN("admin");

    public final String value;

    private UserRoles(String value){this.value = value;}
}
