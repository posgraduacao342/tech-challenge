package api.techchallenge.application.presenters.mappers;

import api.techchallenge.application.presenters.requests.item.CriarItemRequest;
import api.techchallenge.domain.entities.Item;
import api.techchallenge.domain.entities.Pedido;
import api.techchallenge.domain.entities.Produto;
import api.techchallenge.infrastructure.db.entity.ItemEntity;
import api.techchallenge.infrastructure.db.entity.PedidoEntity;
import api.techchallenge.infrastructure.db.entity.ProdutoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ItemMapper {
    private final ProdutoMapper produtoMapper;
    private final GenericMapper itemGenericMapper;

    public ItemEntity toEntity(Item item) {
        ItemEntity itemEntity = itemGenericMapper.toTransform(item, ItemEntity.class);
        itemEntity.setProduto(itemGenericMapper.toTransform(item.getProduto(), ProdutoEntity.class));
        itemEntity.setPedido(itemGenericMapper.toTransform(item.getPedido(), PedidoEntity.class));

        return itemEntity;
    }

    public Item toDomain(ItemEntity itemEntity) {
        var item = itemGenericMapper.toTransform(itemEntity, Item.class);
        item.setProduto(itemGenericMapper.toTransform(itemEntity.getProduto(), Produto.class));
        item.setPedido(itemGenericMapper.toTransform(itemEntity.getPedido(), Pedido.class));

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

    public List<Item> toDomain(List<ItemEntity> itensEntity) {
        var itens = new ArrayList<Item>();
        itensEntity.forEach(itemEntity -> {
            itens.add(toDomain(itemEntity));
        });
        return itens;
    }

    public List<ItemEntity> toEntity(List<Item> itens) {
        var itensEntity = new ArrayList<ItemEntity>();
        itens.forEach(item -> {
            itensEntity.add(toEntity(item));
        });
        return itensEntity;
    }
}
