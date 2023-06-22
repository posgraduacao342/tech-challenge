package api.techchallenge.adapters;

import api.techchallenge.adapters.inbound.entity.ClienteEntity;
import api.techchallenge.adapters.inbound.mapper.ClienteEntityParaClienteMapper;
import api.techchallenge.adapters.outbound.repositories.ClienteRepository;
import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.out.BuscarClientesPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BuscarClientesAdapter implements BuscarClientesPort {
    private final ClienteRepository clienteRepository;

    private final ClienteEntityParaClienteMapper clienteEntityParaClienteMapper;

    @Override
    public List<Cliente> buscarClientes() {
        var clientesEntity = this.clienteRepository.findAll();
        List<Cliente> clientes = new ArrayList<Cliente>();

        for (ClienteEntity clienteEntity: clientesEntity) {
            clientes.add(this.clienteEntityParaClienteMapper.mapper(clienteEntity));
        }
        return clientes;
    }
}
