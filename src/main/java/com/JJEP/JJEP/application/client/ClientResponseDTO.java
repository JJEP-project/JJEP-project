package com.JJEP.JJEP.application.client;

import com.JJEP.JJEP.application.client.children.ChildrenBaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO extends ClientBaseDTO{
    @ToString.Exclude private List<ChildrenBaseDTO> children;
}
