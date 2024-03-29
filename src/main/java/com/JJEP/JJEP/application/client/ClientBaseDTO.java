package com.JJEP.JJEP.application.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

// annotations from lombok to remove boilerplate code
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClientBaseDTO {
    private Long id;
    private String familyName;
    private String forename;
    private String maritalStatus;
    private LocalDate dateOfBirth;
    private Boolean isUkIht;
    private Boolean isSpouseNrb;
    private Boolean isLpa;
    private float primaryProperty;
    private float ukHolidayHome;
    private float btlProperty;
    private float foreignProperty;
    private boolean foreignWill;
    private float brpAssets;
    private float nonBrpAssets;
    private String natureOfBusiness;
    private float investments;
    private float savingsCash;
    private float total;
    private float personalLifeCover;
    private boolean trust;
    private boolean deathBenefitTrust;
    private float deathInService;
    private float pension;
}
