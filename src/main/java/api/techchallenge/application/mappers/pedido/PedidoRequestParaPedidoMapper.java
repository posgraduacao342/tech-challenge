package api.techchallenge.application.mappers.pedido;

import api.techchallenge.application.requests.pedido.CriarPedidoRequest;
import api.techchallenge.domain.core.domain.Pedido;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PedidoRequestParaPedidoMapper {
    public Pedido mapper(CriarPedidoRequest pedidoRequest) {
        var pedido = new Pedido();
        BeanUtils.copyProperties(pedidoRequest, pedido);
        return pedido;
    }
}
