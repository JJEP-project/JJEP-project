package com.JJEP.JJEP.activity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDTO extends ActivityBaseDTO{
    private Long userId;
}
