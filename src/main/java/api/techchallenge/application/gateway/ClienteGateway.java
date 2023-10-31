package api.techchallenge.application.gateway;


import api.techchallenge.application.presenters.mappers.ClienteMapper;
import api.techchallenge.domain.entities.Cliente;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.out.ClienteAdapterPort;
import api.techchallenge.infrastructure.db.entity.ClienteEntity;
import api.techchallenge.infrastructure.db.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.text.MessageFormat.format;

@Component
@AllArgsConstructor
public class ClienteGateway implements ClienteAdapterPort {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> buscarClientes() {
        var clientesEntity = this.clienteRepository.findAll();
        List<Cliente> clientes = new ArrayList<Cliente>();

        for (ClienteEntity clienteEntity : clientesEntity) {
            clientes.add(clienteMapper.toDomain(clienteEntity));
        }
        return clientes;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Cliente> buscarClientePorId(UUID id) throws RecursoNaoEncontratoException {
        var clienteEntity = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));

        return Optional.of(clienteMapper.toDomain(clienteEntity));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Cliente> buscarClientePorCpf(String cpf) throws RecursoNaoEncontratoException {
        var clienteEntity = clienteRepository.findByCpf(String.valueOf(cpf))
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com cpf {0}", cpf)));

        return Optional.of(clienteMapper.toDomain(clienteEntity));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean clienteExiste(String cpf, String email) {
        var clienteEntity = clienteRepository.findByCpf(String.valueOf(cpf));

        return clienteEntity.isPresent();
    }

    @Transactional
    @Override
    public void deletarCliente(UUID id) {
        this.clienteRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Cliente salvarCliente(Cliente cliente) {
        var clienteEntity = clienteMapper.toEntity(cliente);
        return clienteMapper.toDomain(clienteRepository.save(clienteEntity));
    }
}