package com.JJEP.JJEP.admin.activities;


import com.JJEP.JJEP.activity.ActivityRequestDTO;
import com.JJEP.JJEP.activity.ActivityResponseDTO;
import com.JJEP.JJEP.activity.ActivityService;
import com.JJEP.JJEP.application.ApplicationResponseDTO;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;

    @GetMapping("/admin/activities")
    public String adminActivities(Model model, @RequestParam(name = "sortBy", defaultValue = "default") String sortBy) {

        List<ActivityResponseDTO> activities = switch (sortBy) {
            case "oldest" -> activityService.findAllActivitiesOldestFirst();
            case "newest" -> activityService.findAllActivitiesNewestFirst();
            default -> activityService.findAllActivities();
        };

        UserResponseDTO authUser = userService.getAuthenticatedUser();

        model.addAttribute("activities", activities);
        model.addAttribute("currentPage", "activities");
        model.addAttribute("authUser", authUser);

        return "admin/activities";
    }

    @PostMapping("/admin/activities/delete")
    public String deleteActivities(Model model) {

        activityService.deleteActivitiesOlderThanAWeek();
        return "redirect:/admin/activities";

    }

}
