package com.JJEP.JJEP.admin.users;

import com.JJEP.JJEP.application.ApplicationNotFoundException;
import com.JJEP.JJEP.application.ApplicationResponseDTO;
import com.JJEP.JJEP.application.ApplicationService;
import com.JJEP.JJEP.user.UserRegistrationDTO;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
//Controller class for handling administrative tasks related to users
@Controller
public class AdminUserController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/admin/users")
    public String adminUsers(Model model, @RequestParam(name = "sortBy", defaultValue = "default") String sortBy) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        List<UserResponseDTO> users = switch (sortBy) {
            case "oldest" -> userService.findAllUsersOldestFirst();
            case "newest" -> userService.findAllUsersNewestFirst();
            default -> userService.findAllUsers();
        };

        model.addAttribute("users", users);
        model.addAttribute("currentPage", "users");

        return "admin/admin-users";
    }

    @GetMapping("/admin/users/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        UserResponseDTO user = userService.findUserById(id);
        model.addAttribute("user", user);

        try {
            ApplicationResponseDTO form = applicationService.findApplicationByUserId(id);
            model.addAttribute("form", form);
        } catch (ApplicationNotFoundException e) {

        }

        return "admin/user-details";

    }

    @GetMapping("/admin/users/create")
    public String createUser(Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);


        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());

        return "admin/user-create";

    }

    @GetMapping("/admin/users/{id}/edit")
    public String editUserDetails(@PathVariable Long id, Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        UserResponseDTO user = userService.findUserById(id);
        model.addAttribute("user", user);

        try {
            ApplicationResponseDTO form = applicationService.findApplicationByUserId(id);
            model.addAttribute("form", form);
        } catch (ApplicationNotFoundException e) {

        }

        return "admin/user-edit";

    }

    @PostMapping("/admin/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
            return "redirect:/admin/users";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/users/{id}/edit?error";
        }

    }

    @PostMapping("/admin/users/{id}/update")
    public String updateUserDetails(@Valid UserRegistrationDTO user, @PathVariable Long id, Model model, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/admin-users";
        }

        try {
            userService.updateUser(id, user);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
            return "redirect:/admin/users/{id}";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/users/{id}/edit?error";
        }

    }

    @PostMapping("/admin/users/create/handler")
    public String createUserHandler(@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/user-create";
        }

        try {
            userService.saveUser(userRegistrationDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
            return "redirect:/admin/users";
        } catch (Exception e) {
            return "register?error";
        }
    }

}