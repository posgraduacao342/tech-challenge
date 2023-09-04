package api.techchallenge.domain.useCases;

import api.techchallenge.domain.entities.Cliente;
import api.techchallenge.domain.exception.RecursoJaExisteException;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.ClienteUseCasesPort;
import api.techchallenge.domain.ports.out.ClienteAdapterPort;
import api.techchallenge.domain.valueObjects.CPF;
import api.techchallenge.domain.valueObjects.Email;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.text.MessageFormat.format;

public class ClienteUseCases implements ClienteUseCasesPort {
    private final ClienteAdapterPort clienteAdapterPort;

    public ClienteUseCases(ClienteAdapterPort clienteAdapterPort) {
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
    public Cliente buscarClientePorCpf(String cpf) throws RecursoNaoEncontratoException {
        return clienteAdapterPort.buscarClientePorCpf(cpf)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com cpf {0}", cpf)));
    }

    @Override
    public void deletarCliente(UUID id) {
        this.clienteAdapterPort.deletarCliente(id);
    }

    @Override
    public Cliente criarNovoCliente(String nome, String email, String cpf) throws RecursoJaExisteException {
        var emailValido = new Email(email);
        var cpfValido = new CPF(cpf);
        var optionalCliente = this.clienteAdapterPort.clienteExiste(cpf, email);

        if(optionalCliente) {
            throw  new RecursoJaExisteException("Email ou Cpf já possui cadastro");
        }

        var cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpfValido);
        cliente.setEmail(emailValido);
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