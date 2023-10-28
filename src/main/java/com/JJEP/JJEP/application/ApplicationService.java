package com.JJEP.JJEP.application;

import com.JJEP.JJEP.application.client.ClientRequestDTO;
import com.JJEP.JJEP.application.client.ClientService;
import com.JJEP.JJEP.application.client.child.ChildService;
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
    private final ClientService clientService;
    private final ChildService childService;

    // temporary added client service and children service
    // have to think about the desing of the application
    public ApplicationService(IApplicationRepository applicationRepository,
                              ModelMapper modelMapper,
                              ClientService clientService,
                              ChildService childService) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
        this.clientService = clientService;
        this.childService = childService;

        this.modelMapper.getConfiguration().setPropertyCondition(ctx -> {
            // Skip mapping if the source property is null
            return ctx.getSource() != null;
        });
    }

    @Override
    public ApplicationResponseDTO findApplicationById(long id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isEmpty()) {
            throw new ApplicationNotFoundException("Application not found");
        }
        Application application = applicationOptional.get();
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
    @Transactional
    public void saveApplication(ApplicationRequestDTO applicationRequestDTO) {
        Application application = modelMapper.map(applicationRequestDTO, Application.class);
        Application applicationSaved = applicationRepository.save(application);
        List<ClientRequestDTO> clientRequestDTOS = applicationRequestDTO.getClients();
        if (!clientRequestDTOS.isEmpty()) {
            for (ClientRequestDTO clientRequestDTO : clientRequestDTOS) {
                clientRequestDTO.setApplicationId(applicationSaved.getId());
                clientService.saveClient(clientRequestDTO);
//                List<ChildRequestDTO> childRequestDTOS = clientRequestDTO.getChildren();
//                if (childRequestDTOS != null) {
//                    for (ChildRequestDTO childRequestDTO : childRequestDTOS) {
//                        childRequestDTO.setClientId(clientRequestDTO.getId());
//                        childService.saveChild(childRequestDTO);
//                    }
//                }
            }
        }
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
