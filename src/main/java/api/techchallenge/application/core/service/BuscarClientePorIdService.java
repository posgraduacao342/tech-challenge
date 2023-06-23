package api.techchallenge.application.core.service;

import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.in.BuscarClientePorIdServicePort;
import api.techchallenge.application.ports.out.BuscarClientePorIdPort;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

public class BuscarClientePorIdService implements BuscarClientePorIdServicePort {

    private final BuscarClientePorIdPort buscarClientePorIdPort;

    public BuscarClientePorIdService(BuscarClientePorIdPort buscarClientePorIdPort) {
        this.buscarClientePorIdPort = buscarClientePorIdPort;
    }

    @Override
    public Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException {
        return this.buscarClientePorIdPort.buscarClientePorId(id);
    }
}
