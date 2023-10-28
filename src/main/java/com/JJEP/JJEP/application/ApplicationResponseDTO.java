package com.JJEP.JJEP.application;

import com.JJEP.JJEP.application.client.ClientResponseDTO;
import com.JJEP.JJEP.user.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO extends ApplicationBaseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // need to exclude user from toString to avoid infinite recursion
    @ToString.Exclude private User user;
    @ToString.Exclude private List<ClientResponseDTO> clients;
}
