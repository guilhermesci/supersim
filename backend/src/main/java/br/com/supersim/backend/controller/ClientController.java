package br.com.supersim.backend.controller;

import br.com.supersim.backend.dto.ClientDTO;
import br.com.supersim.backend.exception.ClientAlreadyCreatedException;
import br.com.supersim.backend.exception.ClientNotFoundException;
import br.com.supersim.backend.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController implements ClientControllerDocs {

    private final ClientService clientService;

    @GetMapping
    public Page<ClientDTO> listClients(Pageable pageable) {
        return clientService.listAll(pageable);
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.findById(id);
    }

    @GetMapping("/cpf/{cpf}")
    public ClientDTO findByCpf(@PathVariable String cpf) throws ClientNotFoundException {
        return clientService.findByCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody @Valid ClientDTO clientDTO) throws ClientAlreadyCreatedException {
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ClientDTO updateById(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO) throws ClientNotFoundException {
        return clientService.updateById(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ClientNotFoundException {
        clientService.deleteById(id);
    }
}
