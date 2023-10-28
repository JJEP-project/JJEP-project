package com.JJEP.JJEP.Controllers;

import com.JJEP.JJEP.application.*;

import javax.validation.Valid;

import com.JJEP.JJEP.application.ApplicationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 

@Controller
public class ApplicationController {
    @Autowired
    IApplicationService applicationService;

    @GetMapping("/application")
    public String application(Model model) {
        model.addAttribute("formApplication", new ApplicationBaseDTO());
        return "application";
    }
    
    @PostMapping("/application-handler")
    public String applicationHandler(@ModelAttribute("formApplication") @Valid ApplicationBaseDTO formApplication, BindingResult result) {
        if (result.hasErrors()) {
            return "application";
        }

        return "redirect:/";
    }

}