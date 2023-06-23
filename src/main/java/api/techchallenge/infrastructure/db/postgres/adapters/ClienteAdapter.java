package api.techchallenge.infrastructure.db.postgres.adapters;


import api.techchallenge.infrastructure.db.postgres.entity.ClienteEntity;
import api.techchallenge.application.mappers.ClienteEntityParaClienteMapper;
import api.techchallenge.application.mappers.ClienteParaClienteEntityMapper;
import api.techchallenge.infrastructure.db.postgres.repositories.ClienteRepository;
import api.techchallenge.domain.core.domain.Cliente;
import api.techchallenge.domain.ports.out.ClienteAdapterPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ClienteAdapter implements ClienteAdapterPort {
    private final ClienteRepository clienteRepository;

    private final ClienteEntityParaClienteMapper clienteEntityParaClienteMapper;

    private final ClienteParaClienteEntityMapper clienteParaClienteEntityMapper;

    @Override
    public List<Cliente> buscarClientes() {
        var clientesEntity = this.clienteRepository.findAll();
        List<Cliente> clientes = new ArrayList<Cliente>();

        for (ClienteEntity clienteEntity: clientesEntity) {
            clientes.add(this.clienteEntityParaClienteMapper.mapper(clienteEntity));
        }
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException {
        var optionalClienteEntity = this.clienteRepository.findById(id);
        if(optionalClienteEntity.isEmpty()) {
            throw new UserPrincipalNotFoundException("Cliente Não encontrado");
        }
        return this.clienteEntityParaClienteMapper.mapper(optionalClienteEntity.get());
    }

    @Override
    public void deletarCliente(UUID id) {
        this.clienteRepository.deleteById(id);
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        var clienteEntity = this.clienteParaClienteEntityMapper.mapper(cliente);
        return clienteEntityParaClienteMapper.mapper(clienteRepository.save(clienteEntity));
    }
}
