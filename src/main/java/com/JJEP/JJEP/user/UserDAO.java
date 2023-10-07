package com.JJEP.JJEP.user;

import java.util.Optional;

public interface UserDAO {
    int insertUser(User user);
    Optional<User> selectUserById(int id);
    Optional<User> selectUserByUsername(String username);
    Optional<User> selectUserByEmail(String email);
    int updateUserById(int id, User user);
    int deleteUserById(int id);
}