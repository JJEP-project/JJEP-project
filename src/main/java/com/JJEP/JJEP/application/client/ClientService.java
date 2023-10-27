package com.JJEP.JJEP.application.client;

import com.JJEP.JJEP.application.ApplicationNotFoundException;
import org.modelmapper.ModelMapper;

import java.util.Optional;

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
    public void saveClient(ClientRequestDTO clientRequestDTO) {
        Client client = modelMapper.map(clientRequestDTO, Client.class);
        clientRepository.save(client);
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
