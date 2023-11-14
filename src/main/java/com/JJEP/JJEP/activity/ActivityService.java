package com.JJEP.JJEP.activity;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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
    public void saveActivity(ActivityRequestDTO activity) {
        Activity activityToSave = modelMapper.map(activity, Activity.class);
        activityRepository.save(activityToSave);
    }

    @Override
    public List<ActivityResponseDTO> findAllActivitiesNewestFirst() {
        List<Activity> activities = activityRepository.findAllByOrderByActivityDateDesc();
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
    public List<ActivityResponseDTO> findAllActivitiesOldestFirst() {
        List<Activity> activities = activityRepository.findAllByOrderByActivityDateAsc();
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
    }

}
