package com.JJEP.JJEP.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserResponseDTO> findAllUsers();
    void saveUser(UserRegistrationDTO user);

    void registerUser(User user);
}
