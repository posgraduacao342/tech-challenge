package api.techchallenge.application.core.service;

import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.in.BuscarClientesServicePort;
import api.techchallenge.application.ports.out.BuscarClientesPort;

import java.util.List;

public class BuscarClientesService implements BuscarClientesServicePort {
    private final BuscarClientesPort buscarClientesPort;

    public BuscarClientesService(BuscarClientesPort buscarClientesPort) {
        this.buscarClientesPort = buscarClientesPort;
    }

    @Override
    public List<Cliente> buscarClientes() {
        return this.buscarClientesPort.buscarClientes();
    }
}
