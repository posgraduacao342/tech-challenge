package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.core.domain.Cliente;
import api.techchallenge.domain.core.exception.RecursoJaExisteException;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteServicePort {
    List<Cliente> buscarClientes();

    Cliente buscarClientePorId(UUID id) throws RecursoNaoEncontratoException;

    Cliente buscarClientePorCpf(String cpf) throws RecursoNaoEncontratoException;

    void deletarCliente(UUID id);

    Cliente criarNovoCliente(Cliente cliente) throws RecursoJaExisteException;

    Cliente atualizarCliente(Optional<Cliente> clienteOpcional, UUID id) throws RecursoNaoEncontratoException;
}