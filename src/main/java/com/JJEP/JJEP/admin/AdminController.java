package com.JJEP.JJEP.admin;

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
        model.addAttribute("users", adminService.getUsers());

        return "admin-users";
    }

}
