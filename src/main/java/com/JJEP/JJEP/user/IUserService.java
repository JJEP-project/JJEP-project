package com.JJEP.JJEP.user;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> findAllUsers();
    void saveUser(UserRegistrationDTO user);
}
