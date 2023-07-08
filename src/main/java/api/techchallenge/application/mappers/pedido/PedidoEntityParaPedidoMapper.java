package api.techchallenge.application.mappers.pedido;

import api.techchallenge.domain.core.domain.Item;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.infrastructure.db.postgres.entity.PedidoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PedidoEntityParaPedidoMapper {
    public Pedido mapper(PedidoEntity pedidoEntity) {
        var pedido = new Pedido();
        pedidoEntity.getItens().forEach(itensRequest ->{
            var item = new Item();
            var produto = new Produto();
            produto.setId(itensRequest.getProduto().getId());
            BeanUtils.copyProperties(itensRequest, item);
            item.setProduto(produto);
            pedido.adicionarItem(item);
        });
        BeanUtils.copyProperties(pedidoEntity, pedido);
        return pedido;
    }
}
