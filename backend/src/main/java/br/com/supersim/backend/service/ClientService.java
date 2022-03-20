package br.com.supersim.backend.service;

import br.com.supersim.backend.dto.ClientDTO;
import br.com.supersim.backend.entity.Client;
import br.com.supersim.backend.exception.ClientAlreadyCreatedException;
import br.com.supersim.backend.exception.ClientNotFoundException;
import br.com.supersim.backend.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientDTO createClient(ClientDTO clientDTO) throws ClientAlreadyCreatedException {
        verifyIfIsAlreadyCreated(clientDTO.getCpf());
        Client client = new Client(clientDTO);
        Client savedClient = clientRepository.save(client);
        return new ClientDTO(savedClient);
    }

    public ClientDTO findByCpf(String cpf) throws ClientNotFoundException {
        Client foundClient = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException(cpf));
        return new ClientDTO(foundClient);
    }

    public List<ClientDTO> listAll() {
        return clientRepository.findAll()
                .stream()
                .map(x -> new ClientDTO(x))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws ClientNotFoundException {
        verifyIfExists(id);
        clientRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyCreated(String cpf) throws ClientAlreadyCreatedException {
        Optional<Client> optSavedClient = clientRepository.findByCpf(cpf);
        if (optSavedClient.isPresent()) {
            throw new ClientAlreadyCreatedException(cpf);
        }
    }

    private Client verifyIfExists(Long id) throws ClientNotFoundException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }
}
