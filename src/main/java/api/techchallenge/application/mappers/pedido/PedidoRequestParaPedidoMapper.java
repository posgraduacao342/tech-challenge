package api.techchallenge.application.mappers.pedido;

import api.techchallenge.application.requests.pedido.CriarPedidoRequest;
import api.techchallenge.domain.core.domain.Item;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.domain.Produto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PedidoRequestParaPedidoMapper {
    public Pedido mapper(CriarPedidoRequest pedidoRequest) {
        var pedido = new Pedido();

        pedidoRequest.getItens().forEach(itensRequest ->{
            var item = new Item();
            var produto = new Produto();
            produto.setId(itensRequest.getProdutoId());
            BeanUtils.copyProperties(itensRequest, item);
            item.setProduto(produto);
            pedido.adicionarItem(item);
        });
        BeanUtils.copyProperties(pedidoRequest, pedido);
        return pedido;
    }
}
