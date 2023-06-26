package api.techchallenge.application.mappers;

import api.techchallenge.infrastructure.db.postgres.entity.ClienteEntity;
import api.techchallenge.domain.core.domain.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClienteEntityParaClienteMapper {
    public Cliente mapper(ClienteEntity clienteEntity) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteEntity, cliente);
        return cliente;
    }
}
