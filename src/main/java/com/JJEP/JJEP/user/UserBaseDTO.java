package com.JJEP.JJEP.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDTO {
    private String username;
    private String email;
    private String fullName;
    private UserRoles role;
}

