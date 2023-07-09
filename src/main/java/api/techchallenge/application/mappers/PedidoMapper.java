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
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoMapper {
    private final GenericMapper pedidoGenericMapper;

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
        Pedido pedido = pedidoGenericMapper.toTransform(pedidoEntity, Pedido.class);
        pedidoEntity.getItens().forEach(itemEntity ->{
            var item = pedidoGenericMapper.toTransform(itemEntity, Item.class);
            var produto = pedidoGenericMapper.toTransform(itemEntity.getProduto(), Produto.class);
            item.setProduto(produto);
            pedido.adicionarItem(item);
        });
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
