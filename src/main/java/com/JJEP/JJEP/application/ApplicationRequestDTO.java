package com.JJEP.JJEP.application;

import com.JJEP.JJEP.application.client.ClientRequestDTO;
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
public class ApplicationRequestDTO extends ApplicationBaseDTO {
    private Long userId;
    @ToString.Exclude private List<ClientRequestDTO> clients;
}
