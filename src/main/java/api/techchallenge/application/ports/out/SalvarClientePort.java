package api.techchallenge.application.ports.out;

import api.techchallenge.application.core.domain.Cliente;

public interface SalvarClientePort {
    Cliente salvar(Cliente cliente);
}
