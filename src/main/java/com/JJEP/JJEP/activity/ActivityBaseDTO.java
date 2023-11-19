package com.JJEP.JJEP.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// annotations from lombok to remove boilerplate code
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityBaseDTO {
    private String activityMessage;
}
