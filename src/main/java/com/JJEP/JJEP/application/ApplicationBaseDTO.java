package com.JJEP.JJEP.application;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationBaseDTO {

    private Float primaryProperty;
    
    private Float ukHolidayHome;
    
    private Float btlProperty;
    
    private Float foreignProperty;
    
    private boolean foreignWill;
    
    private Float investment;
    
    private Float savingsCash;
    
    private Float total;
    
    @NotNull(message = "Personal Life Cover cannot be null")
    private Float personalLifeCover;
    
    private boolean trust;
    
    @NotNull(message = "Blood Protection cannot be null")
    private Boolean isBloodProtection;
    
    private Boolean isGenerationIht;
    
    @NotBlank(message = "Notes cannot be blank")
    private String notes;
}