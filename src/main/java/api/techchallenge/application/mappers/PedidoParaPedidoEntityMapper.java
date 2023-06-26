package api.techchallenge.application.mappers;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.infrastructure.db.postgres.entity.PedidoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class PedidoParaPedidoEntityMapper {
    public PedidoEntity mapper(Pedido pedido) {
        var pedidoEntity = new PedidoEntity();
        BeanUtils.copyProperties(pedido, pedidoEntity);
        pedidoEntity.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        pedidoEntity.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
        return pedidoEntity;
    }
}
