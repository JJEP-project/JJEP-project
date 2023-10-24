package com.JJEP.JJEP.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationBaseDTO {
    private boolean isBloodProtection;
    private boolean isGenerationIht;
    private String notes;
    private float primaryProperty;
    private float ukHolidayHome;
    private float btlProperty;
    private float foreignProperty;
    private boolean foreignWill;
    private float investment;
    private float savingsCash;
    private float total;
    private float personalLifeCover;
    private boolean trust;
}
