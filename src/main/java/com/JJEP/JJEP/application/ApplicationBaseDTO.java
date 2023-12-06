package com.JJEP.JJEP.application;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


//This class represents a Data Transfer Object (DTO) for storing application-related data.
// annotations from lombok to remove boilerplate code
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationBaseDTO {

    // Property value, must not be null and must be a valid number
    @NotNull(message = "Primary Property cannot be blank")
    @EnsureNumber(message = "Primary Property must be a valid number.")
    private Float primaryProperty;
    
    // UK property, must not be null and must be a valid number
    @NotNull(message = "UK Holiday Home cannot be blank")
    @EnsureNumber(message = "UK Holiday Home must be a valid number.")
    private Float ukHolidayHome;
    
    // BTL property, must not be null and must be a valid number
    @NotNull(message = "BTL Property cannot be blank")
    @EnsureNumber(message = "BTL Property must be a valid number.")
    private Float btlProperty;
    
    // Foreign property, must not be null and must be a valid number
    @NotNull(message = "Foreign Property cannot be blank")
    @EnsureNumber(message = "Foreign Property must be a valid number.")
    private Float foreignProperty;
    
    // Investment property, must not be null and must be a valid number
    @NotNull(message = "Investment cannot be blank")
    @EnsureNumber(message = "Investment must be a valid number.")
    private Float investment;
    
    // Savings & Cash, must not be null and must be a valid number
    @NotNull(message = "Savings & Cash cannot be blank")
    @EnsureNumber(message = "Savings & Cash must be a valid number.")
    private Float savingsCash;

    // Personal Life Cover Property, must not be null and must be a valid number
    @NotNull(message = "Personal Life Cover cannot be blank")
    @EnsureNumber(message = "Personal Life Cover must be a valid number.")
    private Float personalLifeCover;

    // Foreign Will Property, must not be null
    @NotNull(message = "Foreign Will cannot be null")
    private boolean foreignWill;
    
    // Trust Property, must not be null
    @NotNull(message = "Trust cannot be null")
    private boolean trust;
    
    // Blood Protection Property, must not be null
    @NotNull(message = "Blood Protection cannot be null")
    private Boolean isBloodProtection;
    
    // Generation IHT Property, must not be null
    @NotNull(message = "Generation IHT cannot be null")
    private Boolean isGenerationIht;

    private Integer status;

    private String notes;
}