package com.JJEP.JJEP.application.client.child;

import lombok.*;
import lombok.experimental.SuperBuilder;

// annotations from lombok to remove boilerplate code
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChildResponseDTO extends ChildBaseDTO{
    private Long id;
}
