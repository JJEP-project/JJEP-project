package com.JJEP.JJEP.user;

import com.JJEP.JJEP.activity.Activity;
import com.JJEP.JJEP.application.Application;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

// annotations from lombok to remove boilerplate code
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="User")
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username", unique=true)
    private String username;

    @Size(min = 8, message = "Password should contain at least 8 characters")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Full name cannot be blank")
    @Column(name = "full_name")
    private String fullName;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private String role;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude private Application application;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude private Set<Activity> activities;
}
