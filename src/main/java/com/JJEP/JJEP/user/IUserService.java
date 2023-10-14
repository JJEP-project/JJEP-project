package com.JJEP.JJEP.user;

import java.util.List;

public interface IUserService {
    UserResponseDTO findUserById(long id);
    UserResponseDTO findUserByEmail(String email);

    List<UserResponseDTO> findAllUsers();

    void updateUser(long id, UserRegistrationDTO user);

    void saveUser(UserRegistrationDTO user);

    void deleteUser(long id);
}
