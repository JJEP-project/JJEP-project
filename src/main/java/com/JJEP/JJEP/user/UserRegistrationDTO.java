package com.JJEP.JJEP.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

// annotations from lombok to remove boilerplate code
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO extends UserBaseDTO {
    @Size(min = 8, message = "Password should contain at least 8 characters")
    private String password;

    @Size(min = 8, message = "Password confirmation should contain at least 8 characters")
    private String confirmPassword;
}
