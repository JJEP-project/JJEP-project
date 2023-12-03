package com.JJEP.JJEP.application;

import com.JJEP.JJEP.application.client.ClientResponseDTO;
import com.JJEP.JJEP.user.User;
import com.JJEP.JJEP.user.UserResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

// annotations from lombok to remove boilerplate code
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO extends ApplicationBaseDTO {
    private Long id;

    @NotNull(message = "Total cannot be blank")
    @EnsureNumber(message = "Total must be a valid number.")
    private Float total;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // need to exclude user from toString to avoid infinite recursion
    @ToString.Exclude private UserResponseDTO user;
    @ToString.Exclude private List<ClientResponseDTO> clients;
}
