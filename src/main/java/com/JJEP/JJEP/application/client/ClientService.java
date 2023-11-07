package com.JJEP.JJEP.application.client;

import com.JJEP.JJEP.application.ApplicationNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientService(IClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientResponseDTO findClientById(long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new ApplicationNotFoundException("Client not found");
        }
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    @Override
    public void updateClient(long id, ClientResponseDTO clientResponseDTO) {
        Optional<Client> existingClientOptional = clientRepository.findById(id);
        if (existingClientOptional.isEmpty()) {
            throw new ApplicationNotFoundException("Client not found");
        }
        Client existingClient = existingClientOptional.get();
        Client updatedClient = modelMapper.map(clientResponseDTO, Client.class);
        // update existingClient with updated attributes of updatedClient
        modelMapper.map(updatedClient, existingClient);
        clientRepository.updateById(id, existingClient);
    }

    @Override
    public ClientResponseDTO saveClient(ClientRequestDTO clientRequestDTO) {
        Client client = modelMapper.map(clientRequestDTO, Client.class);
        return modelMapper.map(clientRepository.save(client), ClientResponseDTO.class);
    }

    @Override
    public List<ClientResponseDTO> saveAllClients(List<ClientRequestDTO> clientRequestDTOs) {
        List<ClientResponseDTO> clientsResponseDTOs = new ArrayList<>();
        for (ClientRequestDTO clientRequestDTO : clientRequestDTOs) {
            Client client = modelMapper.map(clientRequestDTO, Client.class);
            ClientResponseDTO clientResponseDTO = modelMapper.map(clientRepository.save(client), ClientResponseDTO.class);
            clientsResponseDTOs.add(clientResponseDTO);
        }
        return clientsResponseDTOs;
    }

    @Override
    public void deleteClient(long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new ApplicationNotFoundException("Client not found");
        }
        clientRepository.deleteById(id);
    }
}
