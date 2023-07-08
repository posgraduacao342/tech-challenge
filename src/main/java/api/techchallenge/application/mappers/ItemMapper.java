package api.techchallenge.application.mappers;

import api.techchallenge.application.requests.item.CriarItemRequest;
import api.techchallenge.domain.core.domain.Item;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.infrastructure.db.postgres.entity.ItemEntity;
import api.techchallenge.infrastructure.db.postgres.entity.PedidoEntity;
import api.techchallenge.infrastructure.db.postgres.entity.ProdutoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemMapper {
    private final ProdutoMapper produtoMapper;

    public ItemEntity toEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();

        var pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(item.getPedido().getId());

        var produtoEntity = new ProdutoEntity();
        produtoEntity.setId(item.getProduto().getId());

        itemEntity.setId(item.getId());
        itemEntity.setPedido(pedidoEntity);
        itemEntity.setProduto(produtoEntity);
        itemEntity.setObservacoes(item.getObservacoes());
        itemEntity.setQuantidade(item.getQuantidade());

        return itemEntity;
    }

    public Item toDomain(ItemEntity itemEntity) {
        Item item = new Item();
        item.setId(itemEntity.getId());
        item.setProduto(produtoMapper.toDomain(itemEntity.getProduto()));
        item.setObservacoes(itemEntity.getObservacoes());
        item.setQuantidade(itemEntity.getQuantidade());
        return item;
    }

    public Item toDomain(CriarItemRequest request) {
        Item item = new Item();
        Produto produto = new Produto();
        Pedido pedido = new Pedido();

        produto.setId(request.getProdutoId());
        pedido.setId(request.getPedidoId());

        item.setProduto(produto);
        item.setPedido(pedido);
        item.setObservacoes(request.getObservacoes());
        item.setQuantidade(request.getQuantidade());

        return item;
    }
}
