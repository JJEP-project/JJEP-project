package com.JJEP.JJEP.application;

import java.util.List;

public class ApplicationService implements IApplicationService{
    @Override
    public ApplicationResponseDTO findApplicationById(long id) {
        return null;
    }

    @Override
    public List<ApplicationResponseDTO> findAllApplications() {
        return null;
    }

    @Override
    public void updateApplication(long id, ApplicationResponseDTO applicationResponseDTO) {

    }

    @Override
    public void saveApplication(ApplicationRequestDTO applicationRequestDTO) {

    }

    @Override
    public void deleteApplication(long id) {

    }
}
