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
    public String adminUsers(Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        List<UserResponseDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);

        model.addAttribute("currentPage", "users");

        return "admin/admin-users";
    }

    @GetMapping("/admin/user-details/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        UserResponseDTO user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "admin/user-details";

    }

    @GetMapping("/admin/user-create")
    public String createUser(Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);


        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());

        return "admin/user-create";

    }

    @GetMapping("/admin/user-edit/{id}")
    public String editUserDetails(@PathVariable Long id, Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        UserResponseDTO user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "admin/user-edit";

    }

    @PostMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {

        try {
            userService.deleteUser(id);
            return "redirect:/admin/users";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/user-edit/{id}?error";
        }

    }

    @PostMapping("/admin/user-update/{id}")
    public String updateUserDetails(@Valid UserRegistrationDTO user, @PathVariable Long id, Model model, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/admin-users";
        }

        try {
            userService.updateUser(id, user);
            return "redirect:/admin/user-details/{id}";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/user-delete/{id}?error";
        }

    }

    @PostMapping("/admin/create-user-handler")
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
