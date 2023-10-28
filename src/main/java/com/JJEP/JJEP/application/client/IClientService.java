package com.JJEP.JJEP.application.client;

import java.util.List;

public interface IClientService {
        ClientResponseDTO findClientById(long id);
        void updateClient(long id, ClientResponseDTO clientResponseDTO);
        ClientResponseDTO saveClient(ClientRequestDTO clientRequestDTO);
        List<ClientResponseDTO> saveAllClients(List<ClientRequestDTO> clientRequestDTOs);
        void deleteClient(long id);
}
