package api.techchallenge.adapters.inbound.mapper;

import api.techchallenge.adapters.inbound.entity.ClienteEntity;
import api.techchallenge.application.core.domain.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClienteEntityParaClienteMapper {
    public Cliente mapper(ClienteEntity clienteEntity){
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteEntity, cliente);
        return cliente;
    }
}
