package api.techchallenge.domain.ports.out;

import api.techchallenge.domain.entities.Cliente;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface ClienteAdapterPort {
    List<Cliente> buscarClientes();

    Optional<Cliente> buscarClientePorId(UUID id) throws RecursoNaoEncontratoException;

    Optional<Cliente> buscarClientePorCpf(String cpf) throws RecursoNaoEncontratoException;

    void deletarCliente(UUID id);

    Cliente salvarCliente(Cliente cliente);

    boolean clienteExiste(String cpf, String email);
}
