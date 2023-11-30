package com.JJEP.JJEP.activity;

import com.JJEP.JJEP.user.UserResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

// annotations from lombok to remove boilerplate code
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO extends ActivityBaseDTO{
    private Long id;
    private LocalDateTime activityDate;

    @ToString.Exclude private UserResponseDTO user;
}
