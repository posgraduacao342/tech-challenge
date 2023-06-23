package api.techchallenge.application.core.service;

import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.in.ClienteServicePort;
import api.techchallenge.application.ports.out.ClienteAdapterPort;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

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
    public Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException {
        return this.clienteAdapterPort.buscarClientePorId(id);
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
