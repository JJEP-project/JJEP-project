package com.JJEP.JJEP.user;

public interface UserDAO {
    int insertUser(User user);
    User selectUserById(int id);
    User selectUserByUsername(String username);
    User selectUserByEmail(String email);
    int updateUserById(int id, User user);
    int deleteUserById(int id);
}
