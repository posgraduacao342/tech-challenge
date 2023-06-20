package api.techchallenge.application.ports.out;

import api.techchallenge.application.core.domain.Cliente;

import java.util.List;

public interface BuscarClientesPort {
    List<Cliente> findAll();
}
