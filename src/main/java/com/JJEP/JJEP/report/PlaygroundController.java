package com.JJEP.JJEP.report;

import com.JJEP.JJEP.application.ApplicationRequestDTO;
import com.JJEP.JJEP.application.client.ClientRequestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaygroundController {

    private final IApplicationAnalyzer applicationAnalyzer;
    private final IPdfService IPdfService;

    public PlaygroundController(IApplicationAnalyzer applicationAnalyzer, IPdfService IPdfService) {
        this.applicationAnalyzer = applicationAnalyzer;
        this.IPdfService = IPdfService;
    }

    @GetMapping("/playground")
    public String showPlayground(Model model, @RequestParam(name = "doubledClients", defaultValue = "false") boolean doubledClients) {
        List<ClientRequestDTO> clients = new ArrayList<>();
        clients.add(new ClientRequestDTO());
        if (doubledClients) {
            clients.add(new ClientRequestDTO());
        }
        ApplicationRequestDTO applicationRequestDTO = new ApplicationRequestDTO();
        applicationRequestDTO.setClients(clients);
        model.addAttribute("formApplication", applicationRequestDTO);
        return "playground";
    }

    @PostMapping("/playground/handler")
    public ResponseEntity<byte[]> generateReport(ApplicationRequestDTO applicationRequestDTO, Model model, HttpServletResponse response) {
        List<ReportResponseDTO> reportResponseDTOList = applicationAnalyzer.analyzeApplication(applicationRequestDTO);
        try {
            byte[] pdfBytes = IPdfService.generatePdf(reportResponseDTOList);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (IOException e) {
            model.addAttribute("error", "Error generating PDF");
        }
        return null;
    }
}
