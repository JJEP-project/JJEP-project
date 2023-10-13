package com.JJEP.JJEP.user;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ToString
@Data
@SuperBuilder
public class UserResponseDTO {
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserRoles role;

    public UserResponseDTO(String username, String email, String fullName, UserRoles role) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public UserResponseDTO(Long id, String username, String email, String fullName, LocalDateTime createdAt, LocalDateTime updatedAt, UserRoles role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    public UserResponseDTO() {
    }
}

