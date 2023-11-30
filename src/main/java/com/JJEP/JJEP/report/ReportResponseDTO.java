package com.JJEP.JJEP.report;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportResponseDTO {
    String firstName;
    String lifeCover;
    String personalPension;
    String deathInService;
}
