package com.JJEP.JJEP.application;


import com.JJEP.JJEP.user.UserResponseDTO;

import java.util.List;

public interface IApplicationService {
        ApplicationResponseDTO findApplicationById(long id);
        List<ApplicationResponseDTO> findAllApplications();
        void updateApplication(long id, ApplicationResponseDTO applicationResponseDTO);
        void saveApplication(ApplicationRequestDTO applicationRequestDTO);
        void deleteApplication(long id);

        List<ApplicationResponseDTO> getLastFiveApplications();
}
