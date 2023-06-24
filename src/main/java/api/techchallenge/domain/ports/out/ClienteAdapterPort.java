package api.techchallenge.domain.ports.out;

import api.techchallenge.domain.core.domain.Cliente;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface ClienteAdapterPort {
    List<Cliente> buscarClientes();

    Optional<Cliente> buscarClientePorId(UUID id) throws RecursoNaoEncontratoException;

    void deletarCliente(UUID id);

    Cliente salvarCliente(Cliente cliente);
}
