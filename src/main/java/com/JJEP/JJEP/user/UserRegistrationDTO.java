package com.JJEP.JJEP.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO extends UserBaseDTO {
    @Size(min = 8, message = "Password should contain at least 8 characters")
    private String password;
}
