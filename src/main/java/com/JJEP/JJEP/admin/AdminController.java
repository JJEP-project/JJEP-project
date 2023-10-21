package com.JJEP.JJEP.admin;

import java.util.List;

import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private boolean usersAdded = false;
    private boolean applicationsAdded = false;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @GetMapping("/usersadmin")
    public String adminUsers(Model model) {
        if (!usersAdded) {
            adminService.addToTheUsersList();
            usersAdded = true;
        }

        List<UserResponseDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "admin-users";
    }

    @GetMapping("/applicationsadmin")
    public String adminApplications(Model model) {
        if (!applicationsAdded) {
            adminService.addToTheApplicationsList();
            applicationsAdded = true;
        }

        List<String> forms = adminService.getApplications();
        model.addAttribute("forms", forms);

        return "admin-applications";
    }

}
