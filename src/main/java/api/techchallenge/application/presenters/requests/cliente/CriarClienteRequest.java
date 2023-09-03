package api.techchallenge.application.presenters.requests.cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarClienteRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String cpf;
}