package com.JJEP.JJEP.Controllers;

import com.JJEP.JJEP.Models.LoginForm;
import com.JJEP.JJEP.user.UserRegistrationDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

//This class represents the controller for handling authentication-related requests
@Controller
public class AuthController {
    @Autowired
    UserService userService;

    //This method handles the GET request for the login page
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "auth/login";
    }

    //This method handles the GET request for the registration page
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "auth/register";
    }

    //This method handles the POST request for the login page
    @PostMapping("/login-handler")
    public String loginHandler(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/login";
        }

        return "redirect:/";
    }

    //This method handles the POST request for the registration page
    @PostMapping("/register-handler")
    public String regiserHandler(@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            model.addAttribute("error", "Password mismatch");
            model.addAttribute("userRegistrationDTO", userRegistrationDTO);
            return "auth/register";
        }

        try {
            userService.saveUser(userRegistrationDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful!");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "redirect:register?error";
        }
    }

}
