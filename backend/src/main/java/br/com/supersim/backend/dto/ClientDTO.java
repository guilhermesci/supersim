package br.com.supersim.backend.dto;

import br.com.supersim.backend.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    private String email;

    private Double income;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.cpf = client.getCpf();
        this.name = client.getName();
        this.email = client.getEmail();
        this.income = client.getIncome();
    }
}
