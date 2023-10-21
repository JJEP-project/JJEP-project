package com.JJEP.JJEP.application;

import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequestDTO extends ApplicationBaseDTO {
    private Long userId;
}
