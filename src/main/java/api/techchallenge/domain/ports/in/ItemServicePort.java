package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.core.domain.Item;

import java.util.List;
import java.util.UUID;

public interface ItemServicePort {
    List<Item> buscarItensPorPedido(UUID pedidoId);
    Item buscarItem(UUID itemId);
}