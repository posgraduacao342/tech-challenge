package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Cliente;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.ClienteServicePort;
import api.techchallenge.domain.ports.out.ClienteAdapterPort;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;

public class ClienteService implements ClienteServicePort {
    private final ClienteAdapterPort clienteAdapterPort;

    public ClienteService(ClienteAdapterPort clienteAdapterPort) {
        this.clienteAdapterPort = clienteAdapterPort;
    }

    @Override
    public List<Cliente> buscarClientes() {
        return this.clienteAdapterPort.buscarClientes();
    }

    @Override
    public Cliente buscarClientePorId(UUID id) throws RecursoNaoEncontratoException {
        return clienteAdapterPort.buscarClientePorId(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));
    }

    @Override
    public void deletarCliente(UUID id) {
        this.clienteAdapterPort.deletarCliente(id);
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        return this.clienteAdapterPort.salvarCliente(cliente);
    }
}
