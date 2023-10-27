package com.JJEP.JJEP.admin.users;

import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping("/usersadmin")
    public String adminUsers(Model model) {

        List<UserResponseDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "admin-users";
    }

    @GetMapping("/user-details-admin/{id}")
    public String getUserDetails(@PathVariable int id, Model model) {

        UserResponseDTO user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "user-details";

    }

    @GetMapping("/user-create-admin")
    public String createUser() {

        return "user-create";

    }

    @GetMapping("/user-edit-admin/{id}")
    public String editUserDetails(@PathVariable int id, Model model) {

        UserResponseDTO user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "user-edit";

    }

}
