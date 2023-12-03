package com.JJEP.JJEP.application.client.child;

import lombok.*;
import lombok.experimental.SuperBuilder;

// annotations from lombok to remove boilerplate code
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ChildRequestDTO extends ChildBaseDTO{
    Long clientId;
}
