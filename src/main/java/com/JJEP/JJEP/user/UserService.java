package com.JJEP.JJEP.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            IUserRepository userRepository,
            ModelMapper modelMapper,
            @Lazy PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponseDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return usersDTO;
    }

    @Override
    public void saveUser(UserRegistrationDTO userWithPasswordDTO) {
        User user = modelMapper.map(userWithPasswordDTO, User.class);
        System.out.println(user);
        userRepository.save(user);
    }

    @Override
    public void registerUser(User user) {
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // default role is user
        user.setRole(UserRoles.valueOf("user"));
        System.out.println(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
