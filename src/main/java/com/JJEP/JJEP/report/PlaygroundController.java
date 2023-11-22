package com.JJEP.JJEP.report;

import com.JJEP.JJEP.application.ApplicationRequestDTO;
import com.JJEP.JJEP.application.client.ClientRequestDTO;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaygroundController {

    private final IApplicationAnalyzer applicationAnalyzer;

    public PlaygroundController(IApplicationAnalyzer applicationAnalyzer) {
        this.applicationAnalyzer = applicationAnalyzer;
    }

    @GetMapping("/playground")
    public ApplicationRequestDTO showPlayground(Model model) {
        List<ClientRequestDTO> clients = new ArrayList<>();
        clients.add(new ClientRequestDTO());
        ApplicationRequestDTO applicationRequestDTO = new ApplicationRequestDTO();
        applicationRequestDTO.setClients(clients);
        model.addAttribute("applicationRequestDTO");
        return applicationRequestDTO;
    }

    @GetMapping("/playground/handler")
    public void generateReport(Model model, HttpServletResponse response) {
        try {
            byte[] pdfBytes = generateTestPdf();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=report.pdf");

            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            model.addAttribute("error", "Error generating PDF");
        }
    }

    private byte[] generateTestPdf() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Ya ebal tvoy ro"));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new IOException("Error generating PDF", e);
        }
    }
}
