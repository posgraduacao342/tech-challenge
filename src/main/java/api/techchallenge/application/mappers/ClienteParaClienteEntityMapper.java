package api.techchallenge.application.mappers;

import api.techchallenge.infrastructure.db.postgres.entity.ClienteEntity;
import api.techchallenge.domain.core.domain.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ClienteParaClienteEntityMapper {
    public ClienteEntity mapper(Cliente cliente){
        var clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(cliente, clienteEntity);
        clienteEntity.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        clienteEntity.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
        return clienteEntity;
    }
}
