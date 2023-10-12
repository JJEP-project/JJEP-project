package com.JJEP.JJEP.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    
    @GetMapping("/application")
    public String application() {
        return "application";
    }

}