package api.techchallenge.application.core.service;

import api.techchallenge.application.ports.in.DeletarClienteServicePort;
import api.techchallenge.application.ports.out.DeletarClientePort;

import java.util.UUID;

public class DeletarClienteService implements DeletarClienteServicePort {
    private final DeletarClientePort deletarClientePort;

    public DeletarClienteService(DeletarClientePort deletarClientePort) {
        this.deletarClientePort = deletarClientePort;
    }

    @Override
    public void deletarCliente(UUID id) {
        deletarClientePort.deletar(id);
    }
}
