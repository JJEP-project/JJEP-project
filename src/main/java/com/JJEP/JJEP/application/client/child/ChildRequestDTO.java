package com.JJEP.JJEP.application.client.child;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
public class ChildRequestDTO extends ChildBaseDTO{
    Long clientId;
}
