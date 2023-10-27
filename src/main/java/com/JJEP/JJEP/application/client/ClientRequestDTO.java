package com.JJEP.JJEP.application.client;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO extends ClientBaseDTO{
    private Long applicationId;
}
