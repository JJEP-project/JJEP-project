package com.JJEP.JJEP.Controllers;

import com.JJEP.JJEP.application.ApplicationBaseDTO;
import com.JJEP.JJEP.application.ApplicationRequestDTO;
import com.JJEP.JJEP.application.IApplicationService;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ApplicationController {
    @Autowired
    IApplicationService applicationService;

    @Autowired
    UserService userService;

    @GetMapping("/application")
    public String application(Model model) {
        model.addAttribute("formApplication", new ApplicationBaseDTO());
        return "application";
    }

    @PostMapping("/application-handler")
    public String applicationHandler(@ModelAttribute("formApplication") @Valid ApplicationRequestDTO formApplication, BindingResult result) {
        if (result.hasErrors()) {
            return "application";
        }

        UserResponseDTO currentUser = userService.getAuthenticatedUser();

        try {
            formApplication.setUserId(currentUser.getId());
            applicationService.saveApplication(formApplication);
            return "redirect:/";
        } catch (Exception e) {
            return "application?error";
        }
    }

}