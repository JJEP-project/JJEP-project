package com.JJEP.JJEP.activity;

import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// service annotation is used to mark the class as a service provider for the spring autowire
@Service
public class ActivityService implements IActivityService {
    private final IActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    @Lazy
    UserService userService;

    public ActivityService(IActivityRepository activityRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ActivityResponseDTO> findAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return getActivityResponseDTOs(activities);
    }

    @Override
    public void saveActivity(ActivityRequestDTO activity) {
        Activity activityToSave = modelMapper.map(activity, Activity.class);
        activityRepository.save(activityToSave);
    }

    @Override
    public List<ActivityResponseDTO> findAllActivitiesNewestFirst() {
        List<Activity> activities = activityRepository.findAllByOrderByActivityDateDesc();
        return getActivityResponseDTOs(activities);
    }

    @Override
    public List<ActivityResponseDTO> findAllActivitiesOldestFirst() {
        List<Activity> activities = activityRepository.findAllByOrderByActivityDateAsc();
        return getActivityResponseDTOs(activities);
    }

    // helper method to get the list of activity response DTOs
    private List<ActivityResponseDTO> getActivityResponseDTOs(List<Activity> activities) {
        List<ActivityResponseDTO> activityResponseDTOS = new ArrayList<>();

        if (activities.isEmpty()) {
            throw new ActivityNotFoundException("No activities found");
        }

        for (Activity activity : activities) {
            activityResponseDTOS.add(modelMapper.map(activity, ActivityResponseDTO.class));
        }

        return activityResponseDTOS;
    }

    @Override
    public List<ActivityResponseDTO> getLastFiveActivities() {
        List<Activity> activities = activityRepository.findTop5ByOrderByActivityDateDesc();
        List<ActivityResponseDTO> activityResponseDTOS = new ArrayList<>();

        if (activities.isEmpty()) {
            throw new ActivityNotFoundException("No activities found");
        }

        for (Activity activity : activities) {
            activityResponseDTOS.add(modelMapper.map(activity, ActivityResponseDTO.class));
        }

        return activityResponseDTOS;
    }

    public void deleteActivitiesByUserId(long userId) {
        activityRepository.deleteByUserId(userId);
    }

    public void deleteActivitiesOlderThanAWeek() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        activityRepository.deleteByActivityDateBefore(oneWeekAgo);

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        saveActivity(ActivityRequestDTO
                .builder()
                .userId(authUser.getId())
                .activityMessage("Has deleted an activities older than a week")
                .build()
        );
    }

}
