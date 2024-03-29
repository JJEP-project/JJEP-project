package com.JJEP.JJEP.user;

import com.JJEP.JJEP.activity.ActivityRequestDTO;
import com.JJEP.JJEP.activity.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService implements IUserService {
    // composition
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    ActivityService activityService;

    @Autowired
    public UserService(
            IUserRepository userRepository,
            ModelMapper modelMapper,
            // lazy because of circular dependency
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
        // check if user exists
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

        // create activity
        UserResponseDTO authUser = getAuthenticatedUser();
        activityService.saveActivity(ActivityRequestDTO
                .builder()
                .userId(authUser.getId())
                .activityMessage("Has updated user with id: " + id)
                .build()
        );
    }


    @Override
    public void saveUser(UserRegistrationDTO userWithPasswordDTO) {
        try {
            User user = modelMapper.map(userWithPasswordDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(UserRoles.ROLE_USER.toString());
            userRepository.save(user);

            activityService.saveActivity(ActivityRequestDTO
                    .builder()
                    .userId(user.getId())
                    .activityMessage("Has signed up")
                    .build()
            );

        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("users_username_key"))
                throw new UserAlreadyExistsException("Username already taken");
            else if (e.getMessage().contains("users_email_key"))
                throw new UserAlreadyExistsException("Email already taken");
            else
                throw new RuntimeException("Error saving user");
        }

//        UserResponseDTO authUser = getAuthenticatedUser();
//        activityService.saveActivity(ActivityRequestDTO
//                .builder()
//                .userId(authUser.getId())
//                .activityMessage("Has created new user")
//                .build()
//        );

    }

    @Override
    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        activityService.deleteActivitiesByUserId(id);

        userRepository.deleteById(id);

        UserResponseDTO authUser = getAuthenticatedUser();
        activityService.saveActivity(ActivityRequestDTO
                .builder()
                .userId(authUser.getId())
                .activityMessage("Deleted user with id " + id)
                .build()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        if (existingUserOptional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        User existingUser = existingUserOptional.get();
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), getAuthorities(existingUser));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    public UserResponseDTO getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (authentication == null) {
            throw new RuntimeException("User not authenticated");
        }

        return findUserByEmail(userDetails.getUsername());
    }

    public long getCount() {
        return userRepository.count();
    }
    public long getNewUsersLastWeek() {
        return userRepository.countNewUsersLastWeek();
    }
//    public List<UserResponseDTO> getLastFiveUsers() { return userRepository.findTop5ByOrderByCreatedAtDesc(); }

    @Override
    public List<UserResponseDTO> getLastFiveUsers() {
        List<User> users = userRepository.findTop5ByOrderByCreatedAtDesc();

        List<UserResponseDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return usersDTO;
    }

    @Override
    public List<UserResponseDTO> findAllUsersNewestFirst() {
        List<User> users = userRepository.findAllByOrderByCreatedAtDesc();

        List<UserResponseDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return usersDTO;
    }

    @Override
    public List<UserResponseDTO> findAllUsersOldestFirst() {
        List<User> users = userRepository.findAllByOrderByCreatedAtAsc();

        List<UserResponseDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return usersDTO;
    }



}
