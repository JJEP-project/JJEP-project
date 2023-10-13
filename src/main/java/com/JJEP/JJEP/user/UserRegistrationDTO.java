package com.JJEP.JJEP.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString
public class UserRegistrationDTO extends UserResponseDTO {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Size(min = 8, message = "Password should contain at least 8 characters")
    private String password;

    public UserRegistrationDTO() {

    }

    public UserRegistrationDTO(String username, String email, String fullName, UserRoles role, String password) {
        super(username, email, fullName, role);
        this.password = password;
    }
}
