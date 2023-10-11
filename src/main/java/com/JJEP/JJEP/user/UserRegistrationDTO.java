package com.JJEP.JJEP.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString
public class UserRegistrationDTO extends UserResponseDTO {
    private String password;
}
