package br.com.supersim.backend.controller;

import br.com.supersim.backend.dto.ClientDTO;
import br.com.supersim.backend.exception.ClientAlreadyCreatedException;
import br.com.supersim.backend.exception.ClientNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

@Api("Manages Clients's Loan")
public interface ClientControllerDocs {

    @ApiOperation(value = "Client creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success on client creation."),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    ClientDTO createClient(ClientDTO clientDTO) throws ClientAlreadyCreatedException;

    @ApiOperation(value = "Returns client found by a given cpf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success client found."),
            @ApiResponse(code = 404, message = "Client with given cpf not found.")
    })
    ClientDTO findByCpf(@PathVariable String cpf) throws ClientNotFoundException;

    @ApiOperation(value = "Returns a list of all clients created")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success list of all clients created."),
    })
    Page<ClientDTO> listClients(Pageable pageable);

    @ApiOperation(value = "Delete a client found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success client deleted."),
            @ApiResponse(code = 404, message = "Client with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws ClientNotFoundException;
}