package com.JJEP.JJEP.application;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationBaseDTO {

    @NotNull(message = "Primary Property cannot be blank")
    @Min(value = 0, message = "Primary Property must be greater than or equal to 0")
    private Float primaryProperty;
    
    @NotNull(message = "UK Holiday Home cannot be blank")
    @Min(value = 0, message = "UK Holiday Home must be greater than or equal to 0")
    private Float ukHolidayHome;
    
    @NotNull(message = "BTL Property cannot be blank")
    @Min(value = 0, message = "BTL Property must be greater than or equal to 0")
    private Float btlProperty;
    
    @NotNull(message = "Foreign Property cannot be blank")
    @Min(value = 0, message = "Foreign Property must be greater than or equal to 0")
    private Float foreignProperty;
    
    @NotNull(message = "Foreign Will cannot be null")
    private boolean foreignWill;
    
    @NotNull(message = "Investment cannot be blank")
    @Min(value = 0, message = "Investment must be greater than or equal to 0")
    private Float investment;
    
    @NotNull(message = "Savings & Cash cannot be blank")
    @Min(value = 0, message = "Savings & Cash must be greater than or equal to 0")
    private Float savingsCash;
    
    @NotNull(message = "Total cannot be blank")
    @Min(value = 0, message = "Total must be greater than or equal to 0")
    private Float total;
    
    @NotNull(message = "Personal Life Cover cannot be blank")
    @Min(value = 0, message = "Personal Life Cover must be greater than or equal to 0")
    private Float personalLifeCover;
    
    @NotNull(message = "Trust cannot be null")
    private boolean trust;
    
    @NotNull(message = "Blood Protection cannot be null")
    private Boolean isBloodProtection;
    
    @NotNull(message = "Generation IHT cannot be null")
    private Boolean isGenerationIht;
    
    private String notes;
}