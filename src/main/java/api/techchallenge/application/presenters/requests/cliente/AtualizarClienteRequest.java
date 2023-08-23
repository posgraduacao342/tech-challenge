package api.techchallenge.application.presenters.requests.cliente;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class AtualizarClienteRequest {
    private String nome;

    @Email
    private String email;

    @CPF
    private String cpf;
}
