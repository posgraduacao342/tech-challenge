package api.techchallenge.application.adapters;

import api.techchallenge.application.mappers.ItemMapper;
import api.techchallenge.application.requests.item.AtualizarItemRequest;
import api.techchallenge.domain.core.domain.Item;
import api.techchallenge.domain.ports.out.ItemAdapterPort;
import api.techchallenge.infrastructure.db.entity.ItemEntity;
import api.techchallenge.infrastructure.db.repositories.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ItemAdapter implements ItemAdapterPort {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item salvarItem(Item item) {
        ItemEntity itemEntity = itemMapper.toEntity(item);
        ItemEntity savedItemEntity = itemRepository.save(itemEntity);
        return itemMapper.toDomain(savedItemEntity);
    }

    @Override
    public Item atualizarItem(UUID itemId, AtualizarItemRequest request) {
        var itemEntity = itemRepository.findById(itemId);

        if (request.getObservacoes() != null){
            itemEntity.get().setObservacoes(request.getObservacoes());
        }
        if (request.getQuantidade() != null){
            itemEntity.get().setQuantidade(request.getQuantidade());
        }
        ItemEntity updatedItemEntity = itemRepository.save(itemEntity.get());
        return itemMapper.toDomain(updatedItemEntity);
    }

    @Override
    public void deletarItem(UUID itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<Item> buscarItensPorPedido(UUID pedidoId) {
        List<ItemEntity> itemEntities = itemRepository.findByPedidoId(pedidoId);
        return itemEntities.stream()
                .map(itemMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarItensPorPedido(UUID pedidoId) {
        List<ItemEntity> itemEntities = itemRepository.findByPedidoId(pedidoId);
        itemRepository.deleteAll(itemEntities);
    }

    @Override
    public Item buscarItem(UUID itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        return itemMapper.toDomain(itemEntity);
    }
}