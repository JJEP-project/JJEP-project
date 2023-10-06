package com.JJEP.JJEP.user;

import java.sql.Timestamp;

public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private UserRoles role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
    public User(Long id,
                String username,
                String password,
                String email,
                String fullName,
                Timestamp createdAt,
                Timestamp updatedAt,
                UserRoles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.role = role;
        this.updatedAt = updatedAt;
    }

    public User(String username,
                String password,
                String email,
                String fullName,
                UserRoles role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public User(String username,
                String password,
                String email,
                String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }
}
