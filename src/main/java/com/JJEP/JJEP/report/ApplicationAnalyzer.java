package com.JJEP.JJEP.report;

import com.JJEP.JJEP.application.ApplicationRequestDTO;
import com.JJEP.JJEP.application.ApplicationResponseDTO;
import com.JJEP.JJEP.application.client.ClientRequestDTO;
import com.JJEP.JJEP.application.client.ClientResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// service which allows to analyze some of the application data and return a basic report
@Service
public class ApplicationAnalyzer implements IApplicationAnalyzer{
    @Override
    public List<ReportResponseDTO> analyzeApplication(ApplicationRequestDTO applicationRequestDTO) {
        List<ReportResponseDTO> reportResponseDTOList = new ArrayList<>();

        //analyze joint life cover
        if (applicationRequestDTO.getPersonalLifeCover() == 0 || applicationRequestDTO.isTrust()) {
            reportResponseDTOList.add(ReportResponseDTO.builder()
                    .firstName("Joint")
                    .lifeCover(jointLifeCoverGood)
                    .build());
        }
        else {
            reportResponseDTOList.add(ReportResponseDTO.builder()
                    .firstName("Joint")
                    .lifeCover(String.format(jointLifeCoverConcern, applicationRequestDTO.getPersonalLifeCover()))
                    .build());
        }

        //analyze clients
        for (ClientRequestDTO client: applicationRequestDTO.getClients()) {
            ReportResponseDTO reportResponseDTO = ReportResponseDTO.builder()
                    .firstName(client.getForename())
                    .build();
            //analyze personal life cover
            if (client.getPersonalLifeCover() == 0.0f || client.isTrust()){
                reportResponseDTO.setLifeCover(String.format(personalLifeCoverGood, client.getForename()));
            }
            else {
                reportResponseDTO.setLifeCover(String.format(personalLifeCoverConcern, client.getForename(), client.getPersonalLifeCover()));
            }

            //analyze personal pension
            if (client.getPension() == 0.0f || client.isTrust()){
                reportResponseDTO.setPersonalPension(String.format(personalPensionGood, client.getForename()));
            }
            else {
                reportResponseDTO.setPersonalPension(String.format(personalPensionConcern, client.getForename(), client.getPension()));
            }

            //analyze death in service
            if (client.getDeathInService() == 0.0f || client.isTrust()){
                reportResponseDTO.setDeathInService(String.format(deathInServiceGood, client.getForename()));
            }
            else {
                reportResponseDTO.setDeathInService(String.format(deathInServiceConcern, client.getForename(), client.getDeathInService()));
            }
            reportResponseDTOList.add(reportResponseDTO);
        }
        return reportResponseDTOList;
    }
}
