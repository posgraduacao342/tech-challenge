package api.techchallenge.domain.ports.out;

import api.techchallenge.application.presenters.requests.item.AtualizarItemRequest;
import api.techchallenge.domain.entities.Item;

import java.util.List;
import java.util.UUID;

public interface ItemAdapterPort {
    Item salvarItem(Item item);
    Item atualizarItem(UUID itemId, AtualizarItemRequest request);
    void deletarItem(UUID itemId);
    List<Item> buscarItensPorPedido(UUID pedidoId);
    void deletarItensPorPedido(UUID pedidoId);
    Item buscarItem(UUID itemId);
    List<Item> buscarItemPorIds(List<UUID> ids);
    List<Item> atualizarOuSalvarListaItem(List<Item> itens);
}