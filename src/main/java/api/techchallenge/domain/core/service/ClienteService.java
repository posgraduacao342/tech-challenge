package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Cliente;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.ClienteServicePort;
import api.techchallenge.domain.ports.out.ClienteAdapterPort;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.text.MessageFormat.format;

public class ClienteService implements ClienteServicePort {
    private final ClienteAdapterPort clienteAdapterPort;

    public ClienteService(ClienteAdapterPort clienteAdapterPort) {
        this.clienteAdapterPort = clienteAdapterPort;
    }

    @Override
    public List<Cliente> buscarClientes() {
        return this.clienteAdapterPort.buscarClientes();
    }

    @Override
    public Cliente buscarClientePorId(UUID id) throws RecursoNaoEncontratoException {
        return clienteAdapterPort.buscarClientePorId(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));
    }

    @Override
    public void deletarCliente(UUID id) {
        this.clienteAdapterPort.deletarCliente(id);
    }

    @Override
    public Cliente criarNovoCliente(Cliente cliente) {
        cliente.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        cliente.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

        return this.clienteAdapterPort.salvarCliente(cliente);
    }

    @Override
    public Cliente atualizarCliente(Optional<Cliente> clienteOpcional, UUID id) {
        var cliente = this.buscarClientePorId(id);

        if(clienteOpcional.get().getNome() != null) {
            cliente.setNome(clienteOpcional.get().getNome());
        }
        if(clienteOpcional.get().getCpf() != null) {
            cliente.setCpf(clienteOpcional.get().getCpf());
        }
        if(clienteOpcional.get().getEmail() != null) {
            cliente.setEmail(clienteOpcional.get().getEmail());
        }

        cliente.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

        return this.clienteAdapterPort.salvarCliente(cliente);
    }
}
