package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Item;
import api.techchallenge.domain.ports.in.ItemServicePort;
import api.techchallenge.domain.ports.out.ItemAdapterPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ItemService implements ItemServicePort {
    private final ItemAdapterPort itemAdapter;

    @Override
    public List<Item> buscarItensPorPedido(UUID pedidoId) {
        return itemAdapter.buscarItensPorPedido(pedidoId);
    }

    @Override
    public Item buscarItem(UUID itemId) {
        return itemAdapter.buscarItem(itemId);
    }

    @Override
    public List<Item> atualizarObservacao(List<Item> itensObservacao){
        var ids = new ArrayList<UUID>();
        itensObservacao.forEach(item -> {
            ids.add(item.getId());
        });

        var itens = itemAdapter.buscarItemPorIds(ids);

        itens.forEach(item -> {
            itensObservacao.forEach(itemObservacao -> {
                if (item.getId().equals(itemObservacao.getId())) {
                    item.setObservacoes(itemObservacao.getObservacoes());
                }
            });
        });
        itemAdapter.atualizarOuSalvarListaItem(itens);
        return itens;
    }
}