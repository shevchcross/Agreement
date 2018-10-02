package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.ClientDTO;
import me.alexeyshevchenko.agreement_backend.errors.ClienteNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.ClientEntity;
import me.alexeyshevchenko.agreement_backend.repository.ClientEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

  @Autowired
  private ClientEntityRepository repository;

  public ClientDTO createCliente (ClientDTO cliente) {
    ClientEntity clienteToSave = new ClientEntity(cliente);
    ClientEntity savedCliente = repository.save(clienteToSave);
    return new ClientDTO(savedCliente);
  }

  public ClientDTO getClientById(Long id) throws ClienteNotFoundException {
    Optional<ClientEntity> client = repository.findById(id);
    ClientEntity clientEntity = client.orElseThrow(() -> new ClienteNotFoundException(id));
    return new ClientDTO(clientEntity);
  }

  // сделал т.к. нужен только для теста
  public ClientDTO findClientByEdrpou(String edrpou) throws ClienteNotFoundException {
    Optional<ClientEntity> client = repository.findByedrpou(edrpou);
    ClientEntity clientEntity = client.orElseThrow(() -> new ClienteNotFoundException(edrpou));
    return new ClientDTO(clientEntity);
  }

  public ClientDTO updateClient(ClientDTO client) throws ClienteNotFoundException {
    ClientEntity oldClient = repository.findById(client.getId())
            .orElseThrow(() -> new ClienteNotFoundException(client.getId()));
    oldClient.setDirector(client.getDirector());
    oldClient.setEdrpou(client.getEdrpou());
    oldClient.setName(client.getName());
    repository.save(oldClient);
    return new ClientDTO(oldClient);
  }
  public ClientDTO deleteCliet(Long id) throws ClienteNotFoundException {
    Optional<ClientEntity> existedClient = repository.findById(id);
    ClientEntity client = existedClient.orElseThrow(() -> new ClienteNotFoundException(id));
    repository.delete(client);
    return new ClientDTO(client);
  }
}


