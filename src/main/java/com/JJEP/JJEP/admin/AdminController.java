package com.JJEP.JJEP.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private boolean usersAdded = false;

    @Autowired
    AdminService adminService;

    @GetMapping("/usersadmin")
    public String adminusers(Model model) {
        if (!usersAdded) {
            adminService.addToTheList();
            usersAdded = true;
        }

        List<String> usernames = adminService.getUsers();
        model.addAttribute("usernames", usernames);

        return "admin-users";
    }

}
