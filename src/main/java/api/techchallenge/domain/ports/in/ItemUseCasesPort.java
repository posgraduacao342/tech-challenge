package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.entities.Item;

import java.util.List;
import java.util.UUID;

public interface ItemUseCasesPort {
    List<Item> buscarItensPorPedido(UUID pedidoId);
    Item buscarItem(UUID itemId);
    List<Item> atualizarObservacao(List<Item> itens);
}