package api.techchallenge.application.ports.in;

import api.techchallenge.application.core.domain.Cliente;

public interface SalvarClienteServicePort {
    Cliente salvarCliente(Cliente cliente);
}
