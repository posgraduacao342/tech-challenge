package api.techchallenge.application.mappers;

import api.techchallenge.application.requests.pedido.CriarPedidoRequest;
import api.techchallenge.application.responses.item.ItemResponse;
import api.techchallenge.application.responses.pedido.PedidoResponse;
import api.techchallenge.domain.core.domain.Item;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.infrastructure.db.entity.ItemEntity;
import api.techchallenge.infrastructure.db.entity.PedidoEntity;
import api.techchallenge.infrastructure.db.entity.ProdutoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    public Pedido toDomain(CriarPedidoRequest pedidoRequest) {
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

    public Pedido toDomain(PedidoEntity pedidoEntity) {
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

    public PedidoEntity toEntity(Pedido pedido) {
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

    public PedidoResponse toResponse(Pedido pedido){
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
