package com.JJEP.JJEP.application.client.child;

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
public class ChildBaseDTO {
    private String name;
    private LocalDate dateOfBirth;
}
