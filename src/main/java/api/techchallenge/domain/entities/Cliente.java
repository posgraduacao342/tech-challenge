package api.techchallenge.domain.entities;

import api.techchallenge.domain.valueObjects.CPF;
import api.techchallenge.domain.valueObjects.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends BaseEntity {
    private String nome;
    private Email email;
    private CPF cpf;
    private List<Pedido> pedidos;

    public Cliente(String nome, Email email, CPF cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

}