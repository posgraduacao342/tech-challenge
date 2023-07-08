package api.techchallenge.application.mappers.pedido;

import api.techchallenge.application.responses.item.ItemResponse;
import api.techchallenge.application.responses.pedido.PedidoResponse;
import api.techchallenge.domain.core.domain.Pedido;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PedidoParaPedidoResponse {
    public PedidoResponse mapper(Pedido pedido){
        var pedidoResponse = new PedidoResponse();

        pedido.getItens().forEach(item ->{
            var itemResponse = new ItemResponse();
            BeanUtils.copyProperties(item, itemResponse);
            pedidoResponse.adicionarItem(itemResponse);
        });
        BeanUtils.copyProperties(pedido, pedidoResponse);
        return pedidoResponse;
    }
}
