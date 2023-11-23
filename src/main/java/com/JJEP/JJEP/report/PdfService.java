package com.JJEP.JJEP.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService implements IPdfService {

    @Override
    public byte[] generatePdf(List<ReportResponseDTO> reportResponseDTOList) throws IOException {
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
