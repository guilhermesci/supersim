package br.com.supersim.backend.entity;

import br.com.supersim.backend.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Double income;

    public Client(ClientDTO clientDTO) {
        this.id = clientDTO.getId();
        this.cpf = clientDTO.getCpf();
        this.name = clientDTO.getName();
        this.email = clientDTO.getEmail();
        this.income = clientDTO.getIncome();
    }
}
