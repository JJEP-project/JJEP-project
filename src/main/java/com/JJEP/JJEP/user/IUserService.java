package com.JJEP.JJEP.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    UserResponseDTO findUserById(long id);
    UserResponseDTO findUserByEmail(String email);

    List<UserResponseDTO> findAllUsers();

    List<UserResponseDTO> findAllUsersNewestFirst();
    List<UserResponseDTO> findAllUsersOldestFirst();

    void updateUser(long id, UserRegistrationDTO user);

    void saveUser(UserRegistrationDTO user);

    void deleteUser(long id);

    List<UserResponseDTO> getLastFiveUsers();
}
