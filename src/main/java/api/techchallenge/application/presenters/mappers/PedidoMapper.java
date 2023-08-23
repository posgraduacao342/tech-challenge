package api.techchallenge.application.presenters.mappers;

import api.techchallenge.application.presenters.requests.pedido.CriarPedidoRequest;
import api.techchallenge.application.presenters.responses.cliente.ClienteResponse;
import api.techchallenge.application.presenters.responses.item.ItemResponse;
import api.techchallenge.application.presenters.responses.pedido.PedidoResponse;
import api.techchallenge.domain.entities.Cliente;
import api.techchallenge.domain.entities.Item;
import api.techchallenge.domain.entities.Pedido;
import api.techchallenge.domain.entities.Produto;
import api.techchallenge.infrastructure.db.entity.ClienteEntity;
import api.techchallenge.infrastructure.db.entity.ItemEntity;
import api.techchallenge.infrastructure.db.entity.PedidoEntity;
import api.techchallenge.infrastructure.db.entity.ProdutoEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoMapper {
    private final GenericMapper genericMapper;

    public Pedido toDomain(CriarPedidoRequest pedidoRequest) {
        var pedido = new Pedido();

        if (pedidoRequest.getIdCliente() != null){
            var cliente = new Cliente();
            cliente.setId(pedidoRequest.getIdCliente());
            pedido.setCliente(cliente);
        }

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
        Pedido pedido = genericMapper.toTransform(pedidoEntity, Pedido.class);
        pedidoEntity.getItens().forEach(itemEntity ->{
            var item = genericMapper.toTransform(itemEntity, Item.class);
            var produto = genericMapper.toTransform(itemEntity.getProduto(), Produto.class);
            item.setProduto(produto);
            pedido.adicionarItem(item);
        });

        if (pedidoEntity.getCliente() != null){
            var cliente = genericMapper.toTransform(pedidoEntity.getCliente(), Cliente.class);
            pedido.setCliente(cliente);
        }

        return pedido;
    }

    public PedidoEntity toEntity(Pedido pedido) {
        var pedidoEntity = new PedidoEntity();

        if (pedido.getCliente() != null){
            var cliente = new ClienteEntity();
            pedidoEntity.setCliente(genericMapper.toTransform(pedido.getCliente(), cliente.getClass()));
        }

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

        if (pedido.getCliente() != null){
            var cliente = genericMapper.toTransform(pedido.getCliente(), ClienteResponse.class);
            pedidoResponse.setCliente(cliente);
        }

        BeanUtils.copyProperties(pedido, pedidoResponse);
        return pedidoResponse;
    }
}
