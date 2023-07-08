package api.techchallenge.application.mappers.pedido;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.infrastructure.db.postgres.entity.ItemEntity;
import api.techchallenge.infrastructure.db.postgres.entity.PedidoEntity;
import api.techchallenge.infrastructure.db.postgres.entity.ProdutoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class PedidoParaPedidoEntityMapper {

    public PedidoEntity mapper(Pedido pedido) {
        var pedidoEntity = new PedidoEntity();
        BeanUtils.copyProperties(pedido, pedidoEntity);

        pedido.getItens().forEach(item -> {
            var itemEntity = new ItemEntity();
            BeanUtils.copyProperties(item, itemEntity);

            var produtoEntity = new ProdutoEntity();
            produtoEntity.setId(item.getProduto().getId());
            itemEntity.setProduto(produtoEntity);

            itemEntity.setPedido(pedidoEntity); // Associa o pedido ao item

            pedidoEntity.adicionarItem(itemEntity);
        });

        return pedidoEntity;
    }
}



//public class PedidoParaPedidoEntityMapper {
//    public PedidoEntity mapper(Pedido pedido) {
//        var pedidoEntity = new PedidoEntity();
//
//        pedido.getItens().forEach(item ->{
//            var itemEntity = new ItemEntity();
//            var produto = new ProdutoEntity();
//            produto.setId(item.getProduto().getId());
//            BeanUtils.copyProperties(item, itemEntity);
//            itemEntity.setProduto(produto);
//            pedidoEntity.adicionarItem(itemEntity);
//        });
//        BeanUtils.copyProperties(pedido, pedidoEntity);
//        return pedidoEntity;
//    }
//}
