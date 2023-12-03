package com.JJEP.JJEP.application.client;

import com.JJEP.JJEP.application.client.child.ChildResponseDTO;
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
public class ClientResponseDTO extends ClientBaseDTO{
    @ToString.Exclude private List<ChildResponseDTO> children;
}
