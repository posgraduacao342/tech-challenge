package api.techchallenge.adapters;

import api.techchallenge.adapters.inbound.mapper.ClienteEntityParaClienteMapper;
import api.techchallenge.adapters.outbound.repositories.ClienteRepository;
import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.out.BuscarClientePorIdPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BuscarClientePorIdAdapter implements BuscarClientePorIdPort {
    private final ClienteRepository clienteRepository;

    private final ClienteEntityParaClienteMapper clienteEntityParaClienteMapper;

    @Override
    public Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException {
        var optionalClienteEntity = this.clienteRepository.findById(id);
        if(optionalClienteEntity.isEmpty()) {
            throw new UserPrincipalNotFoundException("Cliente Não encontrado");
        }
        return this.clienteEntityParaClienteMapper.mapper(optionalClienteEntity.get());
    }
}
