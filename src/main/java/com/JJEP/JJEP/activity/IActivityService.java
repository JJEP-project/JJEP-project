package com.JJEP.JJEP.activity;

import com.JJEP.JJEP.application.ApplicationResponseDTO;

import java.util.List;

public interface IActivityService {
    List<ActivityResponseDTO> findAllActivities();
    void saveActivity(ActivityRequestDTO activity);
    List<ActivityResponseDTO> findAllActivitiesNewestFirst();
    List<ActivityResponseDTO> findAllActivitiesOldestFirst();

    List<ActivityResponseDTO> getLastFiveActivities();
}
