package com.JJEP.JJEP.report;

import com.JJEP.JJEP.application.ApplicationRequestDTO;
import com.JJEP.JJEP.application.client.ClientRequestDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaygroundController {

    private final IApplicationAnalyzer applicationAnalyzer;

    public PlaygroundController(IApplicationAnalyzer applicationAnalyzer) {
        this.applicationAnalyzer = applicationAnalyzer;
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
    public void generateReport(ApplicationRequestDTO applicationRequestDTO,Model model, HttpServletResponse response) {
        List<ReportResponseDTO> reportResponseDTOList = applicationAnalyzer.analyzeApplication(applicationRequestDTO);
        try {
            byte[] pdfBytes = generateTestPdf(reportResponseDTOList);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=report.pdf");

            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            model.addAttribute("error", "Error generating PDF");
        }
    }

    private byte[] generateTestPdf(List<ReportResponseDTO> reportResponseDTOList) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            for (ReportResponseDTO reportResponseDTO : reportResponseDTOList) {
                document.add(new Phrase(reportResponseDTO.firstName, FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD)));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph(reportResponseDTO.lifeCover));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph(reportResponseDTO.personalPension));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph(reportResponseDTO.deathInService));
                document.add(new Paragraph("\n\n"));
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new IOException("Error generating PDF", e);
        }
    }
}
