package api.techchallenge.application.presenters.requests.cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarClienteRequest {
    private String nome;

    private String email;

    private String cpf;
}