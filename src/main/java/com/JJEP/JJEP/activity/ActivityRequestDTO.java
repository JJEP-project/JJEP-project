package com.JJEP.JJEP.activity;

import lombok.*;
import lombok.experimental.SuperBuilder;

// annotations from lombok to remove boilerplate code
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDTO extends ActivityBaseDTO{
    private Long userId;
}
