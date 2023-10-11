package com.JJEP.JJEP.user;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum UserRoles {
    @Enumerated(EnumType.STRING)
    user("user"),
    @Enumerated(EnumType.STRING)
    admin("admin");

    public final String value;

    private UserRoles(String value){this.value = value;}
}
