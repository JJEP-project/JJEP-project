package com.JJEP.JJEP.admin;

import java.util.List;

import com.JJEP.JJEP.activity.ActivityService;
import com.JJEP.JJEP.application.Application;
import com.JJEP.JJEP.application.ApplicationResponseDTO;
import com.JJEP.JJEP.application.ApplicationService;
import com.JJEP.JJEP.user.User;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ActivityService activityService;

    @GetMapping("/admin")
    public String homeAdmin(Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);

        model.addAttribute("currentPage", "home");

        model.addAttribute("usersCount", userService.getCount());
        model.addAttribute("applicationsCount", applicationService.getCount());
        model.addAttribute("newUsersLastWeek", userService.getNewUsersLastWeek());
        model.addAttribute("newApplicationsLastWeek", applicationService.getNewApplicationsLastWeek());
        model.addAttribute("users", userService.getLastFiveUsers());
        model.addAttribute("forms", applicationService.getLastFiveApplications());
        model.addAttribute("activities", activityService.getLastFiveActivities());

        return "admin/home";
    }

}
