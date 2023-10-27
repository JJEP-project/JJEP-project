package com.JJEP.JJEP.application.client;

public interface IClientService {
        ClientResponseDTO findClientById(long id);
        void updateClient(long id, ClientResponseDTO clientResponseDTO);
        void saveClient(ClientRequestDTO clientRequestDTO);
        void deleteClient(long id);
}
