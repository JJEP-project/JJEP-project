package com.JJEP.JJEP.admin.applications;

import com.JJEP.JJEP.application.ApplicationResponseDTO;
import com.JJEP.JJEP.application.ApplicationService;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminApplicationController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    UserService userService;

    @GetMapping("/applicationsadmin")
    public String adminApplications(Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        List<ApplicationResponseDTO> forms = applicationService.findAllApplications();
        model.addAttribute("forms", forms);

        model.addAttribute("currentPage", "applications");

        return "admin/admin-applications";
    }

    @GetMapping("/application-details-admin/{id}")
    public String getApplicationDetails(@PathVariable int id, Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        ApplicationResponseDTO form = applicationService.findApplicationById(id);
        model.addAttribute("form", form);


        return "admin/application-details";

    }

}
