package api.techchallenge.application.presenters.responses.cliente;

import lombok.Data;

import java.util.UUID;

@Data
public class ClienteResponse {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
}
