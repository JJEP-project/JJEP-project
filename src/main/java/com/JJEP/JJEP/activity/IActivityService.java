package com.JJEP.JJEP.activity;

import com.JJEP.JJEP.application.ApplicationResponseDTO;

import java.util.List;
//Interface defining the contract for activity-related services
public interface IActivityService {
    List<ActivityResponseDTO> findAllActivities();
    void saveActivity(ActivityRequestDTO activity);
    List<ActivityResponseDTO> findAllActivitiesNewestFirst();
    List<ActivityResponseDTO> findAllActivitiesOldestFirst();

    List<ActivityResponseDTO> getLastFiveActivities();
}
