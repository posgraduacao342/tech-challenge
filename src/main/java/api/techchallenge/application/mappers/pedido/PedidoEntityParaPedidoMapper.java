package api.techchallenge.application.mappers.pedido;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.infrastructure.db.entity.PedidoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PedidoEntityParaPedidoMapper {
    public Pedido mapper(PedidoEntity pedidoEntity) {
        var pedido = new Pedido();
        BeanUtils.copyProperties(pedidoEntity, pedido);
        return pedido;
    }
}
