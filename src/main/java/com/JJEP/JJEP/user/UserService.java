package com.JJEP.JJEP.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        this.modelMapper.getConfiguration().setPropertyCondition(ctx -> {
            // Skip mapping if the source property is null
            return ctx.getSource() != null;
        });
    }

    @Override
    public UserResponseDTO findUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return modelMapper.map(user, UserResponseDTO.class);
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
    @Transactional
    public void updateUser(long id, UserRegistrationDTO userRegistrationDTO) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User existingUser = existingUserOptional.get();
        User updatedUser = modelMapper.map(userRegistrationDTO, User.class);
        // update existingUser with updated attributes of updatedUser
        modelMapper.map(updatedUser, existingUser);
        // because we updated existingUser, we need to save it
        userRepository.updateById(id, existingUser);
    }


    @Override
    public void saveUser(UserRegistrationDTO userWithPasswordDTO) {
        // temp solution
        try {
            User user = modelMapper.map(userWithPasswordDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(UserRoles.ROLE_USER.toString());
            userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("users_username_key"))
                throw new UserAlreadyExistsException("Username already taken");
            else if (e.getMessage().contains("users_email_key"))
                throw new UserAlreadyExistsException("Email already taken");
            else
                throw new RuntimeException("Error saving user");
        }
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        if (existingUserOptional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        User existingUser = existingUserOptional.get();
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
    }

    public UserResponseDTO getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (authentication == null) {
            throw new RuntimeException("User not authenticated");
        }

        System.out.println(userDetails);
        return findUserByEmail(userDetails.getUsername());
    }
}
