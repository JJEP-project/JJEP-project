package com.JJEP.JJEP.application;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService implements IApplicationService{
    private final IApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    public ApplicationService(IApplicationRepository applicationRepository,
                              ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setPropertyCondition(ctx -> {
            // Skip mapping if the source property is null
            return ctx.getSource() != null;
        });
    }

    @Override
    public ApplicationResponseDTO findApplicationById(long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isEmpty()) {
            throw new ApplicationNotFoundException("Application not found");
        }
        return modelMapper.map(application, ApplicationResponseDTO.class);
    }

    @Override
    public List<ApplicationResponseDTO> findAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationResponseDTO> applicationResponseDTOS = new ArrayList<>();

        if (applications.isEmpty()) {
            throw new ApplicationNotFoundException("No applications found");
        }

        for (Application application : applications) {
            applicationResponseDTOS.add(modelMapper.map(application, ApplicationResponseDTO.class));
        }
        return applicationResponseDTOS;
    }

    @Transactional
    @Override
    public void updateApplication(long id, ApplicationResponseDTO applicationResponseDTO) {
        Optional<Application> existingApplicationOptional = applicationRepository.findById(id);
        if (existingApplicationOptional.isEmpty()) {
            throw new ApplicationNotFoundException("Application not found");
        }
        Application existingApplication = existingApplicationOptional.get();
        Application updatedApplication = modelMapper.map(applicationResponseDTO, Application.class);
        // update existingApplication with updated attributes of updatedApplication
        modelMapper.map(updatedApplication, existingApplication);
        // because we updated existingApplication, we can save it with updates
        applicationRepository.updateById(id, existingApplication);
    }
    @Override
    public void saveApplication(ApplicationRequestDTO applicationRequestDTO) {
        Application application = modelMapper.map(applicationRequestDTO, Application.class);
        System.out.println(application);
        applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isEmpty()) {
            throw new ApplicationNotFoundException("Application not found");
        }
        applicationRepository.deleteById(id);
    }
}
