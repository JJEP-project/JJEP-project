package com.JJEP.JJEP.user;

import com.JJEP.JJEP.activity.ActivityResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserResponseDTO extends UserBaseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ToString.Exclude private ActivityResponseDTO activityResponseDTO;
}

