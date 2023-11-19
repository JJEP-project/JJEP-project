package com.JJEP.JJEP.activity;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

// service annotation is used to mark the class as a service provider for the spring autowire
@Service
public class ActivityService implements IActivityService{
    private final IActivityRepository activityRepository;
    private final ModelMapper modelMapper;

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
}
