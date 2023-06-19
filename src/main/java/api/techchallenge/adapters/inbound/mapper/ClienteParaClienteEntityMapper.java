package api.techchallenge.adapters.inbound.mapper;

import api.techchallenge.adapters.inbound.entity.ClienteEntity;
import api.techchallenge.application.core.domain.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ClienteParaClienteEntityMapper {
    public ClienteEntity mapper(Cliente cliente){
        var clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(cliente, clienteEntity);
        clienteEntity.setDataCricao(LocalDateTime.now(ZoneId.of("UTC")));
        clienteEntity.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
        return clienteEntity;
    }
}
