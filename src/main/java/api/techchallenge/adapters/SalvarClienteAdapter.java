package api.techchallenge.adapters;


import api.techchallenge.adapters.inbound.mapper.ClienteEntityParaClienteMapper;
import api.techchallenge.adapters.inbound.mapper.ClienteParaClienteEntityMapper;
import api.techchallenge.adapters.outbound.repositories.ClienteRepository;
import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.out.SalvarClientePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SalvarClienteAdapter implements SalvarClientePort {
    private final ClienteRepository clienteRepository;

    private final ClienteParaClienteEntityMapper clienteParaClienteEntityMapper;

    private final ClienteEntityParaClienteMapper clienteEntityParaClienteMapper;

    @Override
    public Cliente salvar(Cliente cliente) {
        var clienteEntity = clienteParaClienteEntityMapper.mapper(cliente);
        return clienteEntityParaClienteMapper.mapper(clienteRepository.save(clienteEntity));
    }
}
