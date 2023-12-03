package com.JJEP.JJEP.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

// annotations from lombok to remove boilerplate code
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Full name cannot be blank")
    private String fullName;
    private UserRoles role;
}

