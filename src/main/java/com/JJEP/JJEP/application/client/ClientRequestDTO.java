package com.JJEP.JJEP.application.client;

import com.JJEP.JJEP.application.client.child.ChildRequestDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

// annotations from lombok to remove boilerplate code
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO extends ClientBaseDTO{
    private Long applicationId;
    @ToString.Exclude private List<ChildRequestDTO> children;
}
