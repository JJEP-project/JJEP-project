package com.JJEP.JJEP.user;

import com.JJEP.JJEP.config.MapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(IUserRepository userRepository,
                       ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponseDTO> findAllUsers()
    {
        List<User> users = userRepository.findAll();

        List<UserResponseDTO> usersDTO = new ArrayList<>();
        for (User user: users){
            usersDTO.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return usersDTO;
    }

    @Override
    public void saveUser(UserRegistrationDTO userWithPasswordDTO){
        User user = modelMapper.map(userWithPasswordDTO, User.class);
        System.out.println(user);
        userRepository.save(user);
    }
}
