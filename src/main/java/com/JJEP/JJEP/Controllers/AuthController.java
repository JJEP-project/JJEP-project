package com.JJEP.JJEP.Controllers;

import com.JJEP.JJEP.Models.LoginForm;
import com.JJEP.JJEP.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/login-handler")
    public String loginHandler(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) { return "login"; }

        return "redirect:/";

    }

    @PostMapping("/register-handler")
    public String regiserHandler(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) { return "register"; }

        return "redirect:/";

    }

}
