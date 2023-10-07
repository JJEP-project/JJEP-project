package com.JJEP.JJEP.user;

import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class UserWithPasswordDTO extends UserDTO {
    public String password;

    public UserWithPasswordDTO(Long id, String username, String email, String fullName, LocalDateTime createdAt, LocalDateTime updatedAt, UserRoles role, String password) {
        super(id, username, email, fullName, createdAt, updatedAt, role);
        this.password = password;
    }

    public UserWithPasswordDTO(String username, String email, String fullName, UserRoles role, String password) {
        super(username, email, fullName, role);
        this.password = password;
    }

    public UserWithPasswordDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
