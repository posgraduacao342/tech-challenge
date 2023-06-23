package api.techchallenge.application.ports.out;

import api.techchallenge.application.core.domain.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

public interface ClienteAdapterPort {
    List<Cliente> buscarClientes();

    Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException;

    void deletarCliente(UUID id);

    Cliente salvarCliente(Cliente cliente);
}
