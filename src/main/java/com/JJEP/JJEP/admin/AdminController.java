package com.JJEP.JJEP.admin;

import java.util.List;

import com.JJEP.JJEP.application.Application;
import com.JJEP.JJEP.application.ApplicationResponseDTO;
import com.JJEP.JJEP.application.ApplicationService;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    UserService userService;

    @GetMapping("/usersadmin")
    public String adminUsers(Model model) {

        List<UserResponseDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "admin-users";
    }

    @GetMapping("/applicationsadmin")
    public String adminApplications(Model model) {


        List<ApplicationResponseDTO> forms = applicationService.findAllApplications();
        model.addAttribute("forms", forms);

        return "admin-applications";
    }

}
