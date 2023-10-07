package com.JJEP.JJEP.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final ModelMapper modelMapper;


    public UserService(UserDAO userDAO, ModelMapper modelMapper) {
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
    }

    public void addUser(UserWithPasswordDTO user) {
        User user1 = modelMapper.map(user, User.class);
        int result = userDAO.insertUser(user1);
        if (result!=1){
            throw new IllegalStateException("User not added");
        }
    }

    public UserDTO getUserById(int id){
        User user = userDAO.selectUserById(id).orElseThrow(() -> new IllegalStateException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }
}
