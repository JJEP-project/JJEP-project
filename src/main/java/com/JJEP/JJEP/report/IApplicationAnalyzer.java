package com.JJEP.JJEP.report;

import com.JJEP.JJEP.application.ApplicationRequestDTO;
import com.JJEP.JJEP.application.ApplicationResponseDTO;

import java.util.List;

public interface IApplicationAnalyzer {
    String personalLifeCoverGood = "No obvious life cover issues for %s";
    String personalLifeCoverConcern = """
            You have told us that %s has £"%.2f" in personal life cover that may not be directed into trust.
            On death, this may fall into your estate, increasing its value by this amount.
            This needs to be investigated.
            """;
    String jointLifeCoverGood = "No obvious life cover issues for you";
    String jointLifeCoverConcern = """
            You have told us that you both have £"%.2f" in joint life cover that may not be directed into trust.
            On death, this may fall into your estate, increasing its value by this amount.
            This needs to be investigated.
            """;
    String personalPensionGood = "No obvious personal pension issues for %s";
    String personalPensionConcern = """
            You have told us that %s has £"%.2f" in personal pension that may not be directed into trust.
            On death, this may fall into your beneficiaries' estate(s), increasing them by this value.
            This needs to be investigated.
            """;
    String deathInServiceGood = "No obvious death in service issues for %s";
    String deathInServiceConcern = """
            You have told us that %s has £"%.2f" in death in service that may not be directed into trust.
            On death, this may fall into your beneficiaries' estate(s), increasing its value by this amount.
            This needs to be investigated.
            """;


    List<ReportResponseDTO> analyzeApplication(ApplicationRequestDTO applicationRequestDTO);
}
