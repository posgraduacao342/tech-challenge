package api.techchallenge.application.ports.in;

import api.techchallenge.application.core.domain.Cliente;

import java.util.List;

public interface BuscarClientesServicePort {
    List<Cliente> buscarClientes();
}
