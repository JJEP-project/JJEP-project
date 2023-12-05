package com.JJEP.JJEP.report;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportResponseDTO {
    // dto which transfers data about a result of the report
    String firstName;
    String lifeCover;
    String personalPension;
    String deathInService;
}
