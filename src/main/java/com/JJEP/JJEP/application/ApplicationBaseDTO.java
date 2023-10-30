package com.JJEP.JJEP.application;

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

    @NotNull(message = "Primary Property cannot be blank")
    @EnsureNumber(message = "Primary Property must be number.")
    private Float primaryProperty;
    
    @NotNull(message = "UK Holiday Home cannot be blank")
    @EnsureNumber(message = "UK Holiday Home must be number.")
    private Float ukHolidayHome;
    
    @NotNull(message = "BTL Property cannot be blank")
    @EnsureNumber(message = "BTL Property must be number.")
    private Float btlProperty;
    
    @NotNull(message = "Foreign Property cannot be blank")
    @EnsureNumber(message = "Foreign Property must be number.")
    private Float foreignProperty;
    
    @NotNull(message = "Investment cannot be blank")
    @EnsureNumber(message = "Investment must be number.")
    private Float investment;
    
    @NotNull(message = "Savings & Cash cannot be blank")
    @EnsureNumber(message = "Savings & Cash must be number.")
    private Float savingsCash;
    
    @NotNull(message = "Total cannot be blank")
    @EnsureNumber(message = "Total must be number.")
    private Float total;
    
    @NotNull(message = "Personal Life Cover cannot be blank")
    @EnsureNumber(message = "Personal Life Cover must be number.")
    private Float personalLifeCover;

    @NotNull(message = "Foreign Will cannot be null")
    private boolean foreignWill;
    
    @NotNull(message = "Trust cannot be null")
    private boolean trust;
    
    @NotNull(message = "Blood Protection cannot be null")
    private Boolean isBloodProtection;
    
    @NotNull(message = "Generation IHT cannot be null")
    private Boolean isGenerationIht;

    private String notes;
}