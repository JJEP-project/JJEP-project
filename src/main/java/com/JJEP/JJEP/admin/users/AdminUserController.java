package com.JJEP.JJEP.admin.users;

import com.JJEP.JJEP.user.UserRegistrationDTO;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminUserController {

    @Autowired
    UserService userService;

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

        return "admin/user-edit";

    }

    @PostMapping("/admin/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {

        try {
            userService.deleteUser(id);
            return "redirect:/admin/users";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/users/{id}/edit?error";
        }

    }

    @PostMapping("/admin/users/{id}/update")
    public String updateUserDetails(@Valid UserRegistrationDTO user, @PathVariable Long id, Model model, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/admin-users";
        }

        try {
            userService.updateUser(id, user);
            return "redirect:/admin/users/{id}";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/users/{id}/edit?error";
        }

    }

    @PostMapping("/admin/users/create/handler")
    public String createUserHandler(@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/user-create";
        }

        try {
            userService.saveUser(userRegistrationDTO);
            return "redirect:/admin/users";
        } catch (Exception e) {
            return "register?error";
        }
    }

}