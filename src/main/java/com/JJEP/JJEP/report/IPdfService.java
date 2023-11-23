package com.JJEP.JJEP.report;

import java.io.IOException;
import java.util.List;

public interface IPdfService {
    byte[] generatePdf(List<ReportResponseDTO> reportResponseDTOList) throws IOException;
}
