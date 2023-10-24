package com.JJEP.JJEP.Controllers;

import com.JJEP.JJEP.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.JJEP.JJEP.application.ApplicationBaseDTO; 

@Controller
public class ApplicationController {
    IApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/application")
    public String application(Model model) {
        model.addAttribute("formApplication", new ApplicationBaseDTO());
        return "application";
    }
    
}