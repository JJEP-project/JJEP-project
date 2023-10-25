package com.JJEP.JJEP.application;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationBaseDTO {

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
    
    private Boolean isBloodProtection;
    
    private Boolean isGenerationIht;
    
    @NotBlank(message = "Notes cannot be blank")
    private String notes;
}