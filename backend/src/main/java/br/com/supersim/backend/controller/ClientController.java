package br.com.supersim.backend.controller;

import br.com.supersim.backend.dto.ClientDTO;
import br.com.supersim.backend.exception.ClientAlreadyCreatedException;
import br.com.supersim.backend.exception.ClientNotFoundException;
import br.com.supersim.backend.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController implements ClientControllerDocs {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody @Valid ClientDTO clientDTO) throws ClientAlreadyCreatedException {
        return clientService.createClient(clientDTO);
    }

    @GetMapping("/{cpf}")
    public ClientDTO findByCpf(@PathVariable String cpf) throws ClientNotFoundException {
        return clientService.findByCpf(cpf);
    }

    @GetMapping
    public List<ClientDTO> listClients() {
        return clientService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ClientNotFoundException {
        clientService.deleteById(id);
    }
}
