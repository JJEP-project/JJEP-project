package com.JJEP.JJEP.user;

import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @SuppressWarnings("unused")
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
