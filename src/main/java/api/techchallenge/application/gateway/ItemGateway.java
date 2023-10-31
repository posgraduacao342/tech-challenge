package api.techchallenge.application.gateway;

import api.techchallenge.application.presenters.mappers.GenericMapper;
import api.techchallenge.application.presenters.mappers.ItemMapper;
import api.techchallenge.application.presenters.requests.item.AtualizarItemRequest;
import api.techchallenge.domain.entities.Item;
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
public class ItemGateway implements ItemAdapterPort {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final GenericMapper itemGenericMapper;

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

    @Override
    public List<Item> buscarItemPorIds(List<UUID> ids) {
        var itemEntity = itemRepository.findByIdIn(ids);
        return itemMapper.toDomain(itemEntity);
    }

    @Override
    public List<Item> atualizarOuSalvarListaItem(List<Item> itens) {
        var itensEntity = itemMapper.toEntity(itens);
        var itensSalvos = itemRepository.saveAll(itensEntity);
        return itemMapper.toDomain(itensSalvos);
    }
}