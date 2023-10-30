package com.JJEP.JJEP.Controllers;

import com.JJEP.JJEP.Models.LoginForm;
import com.JJEP.JJEP.user.User;
import com.JJEP.JJEP.user.UserRegistrationDTO;
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
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping("/login-handler")
    public String loginHandler(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }

        return "redirect:/";
    }

    @PostMapping("/register-handler")
    public String regiserHandler(@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult result,Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.saveUser(userRegistrationDTO);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "redirect:register?error";
        }
    }

}
