package api.techchallenge.application.ports.in;

import api.techchallenge.application.core.domain.Cliente;

import java.util.UUID;

public interface DeletarClienteServicePort {
    void deletarCliente(UUID id);
}
