package api.techchallenge.application.mappers.pedido;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.infrastructure.db.postgres.entity.PedidoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class PedidoParaPedidoEntityMapper {
    public PedidoEntity mapper(Pedido pedido) {
        var pedidoEntity = new PedidoEntity();
        BeanUtils.copyProperties(pedido, pedidoEntity);
        return pedidoEntity;
    }
}
