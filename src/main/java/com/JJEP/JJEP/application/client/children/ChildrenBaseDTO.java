package com.JJEP.JJEP.application.client.children;

import com.JJEP.JJEP.application.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenBaseDTO {
    private Long id;
    private Client client_1;
    private Client client_2;
    private String name;
    private LocalDate dateOfBirth;
}
