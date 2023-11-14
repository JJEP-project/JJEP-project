package com.JJEP.JJEP.activity;

import java.util.List;

public interface IActivityService {
    List<ActivityResponseDTO> findAllActivities();
    void saveActivity(ActivityRequestDTO activity);
    List<ActivityResponseDTO> findAllActivitiesNewestFirst();
    List<ActivityResponseDTO> findAllActivitiesOldestFirst();
}
