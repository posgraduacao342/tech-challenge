package api.techchallenge.application.mappers;

import api.techchallenge.application.requests.cliente.CriarClienteRequest;
import api.techchallenge.domain.core.domain.Cliente;
import api.techchallenge.infrastructure.db.postgres.entity.ClienteEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toDomain(CriarClienteRequest clienteRequest) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        return cliente;
    }
    public Cliente toDomain(ClienteEntity clienteEntity) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteEntity, cliente);
        return cliente;
    }

    public ClienteEntity toEntity(Cliente cliente){
        var clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(cliente, clienteEntity);
        return clienteEntity;
    }
}
