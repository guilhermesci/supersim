package br.com.supersim.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientAlreadyCreatedException extends Exception{

    public ClientAlreadyCreatedException(String cpf) {
        super(String.format("Client with cpf %s already created.", cpf));
    }
}
