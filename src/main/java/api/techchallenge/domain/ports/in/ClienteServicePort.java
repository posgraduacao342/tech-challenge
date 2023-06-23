package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.core.domain.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

public interface ClienteServicePort {
    List<Cliente> buscarClientes();

    Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException;

    void deletarCliente(UUID id);

    Cliente salvarCliente(Cliente cliente);
}