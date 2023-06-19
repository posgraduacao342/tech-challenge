package api.techchallenge.application.core.service;

import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.in.SalvarClienteServicePort;
import api.techchallenge.application.ports.out.SalvarClientePort;

public class SalvarClienteService implements SalvarClienteServicePort {
    private final SalvarClientePort salvarClientePort;

    public SalvarClienteService(SalvarClientePort salvarClientePort) {
        this.salvarClientePort = salvarClientePort;
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        return salvarClientePort.salvar(cliente);
    }
}
