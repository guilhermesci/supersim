package br.com.supersim.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends Exception{

    public ClientNotFoundException(Long id) {
        super(String.format("Client with id %s not found.", id));
    }

    public ClientNotFoundException(String cpf) {
        super(String.format("Client with cpf %s not found.", cpf));
    }
}
