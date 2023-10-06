package com.JJEP.JJEP.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user) {
        int result = userDAO.insertUser(user);
        if (result!=1){
            throw new IllegalStateException("User not added");
        }
    }
}
