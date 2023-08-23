package api.techchallenge.application.controllers;

import api.techchallenge.application.presenters.mappers.GenericMapper;
import api.techchallenge.application.presenters.requests.item.AtualizarObservacaoItemRequest;
import api.techchallenge.application.presenters.responses.item.ItemResponse;
import api.techchallenge.domain.entities.Item;
import api.techchallenge.domain.ports.in.ItemServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/itens")
@AllArgsConstructor
public class ItemController {
    private final ItemServicePort itemService;
    private final GenericMapper itemGenericMapper;

    @GetMapping("/porPedido/{pedidoId}")
    public ResponseEntity<List<Item>> buscarItensPorPedido(@PathVariable UUID pedidoId) {
        List<Item> items = itemService.buscarItensPorPedido(pedidoId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> buscarItem(@PathVariable UUID itemId) {
        Item item = itemService.buscarItem(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PatchMapping(value = "/observacao")
    public List<ItemResponse> atualizarObservacao(@RequestBody @Valid List<AtualizarObservacaoItemRequest> itemRequestPatch){
        var itens = itemGenericMapper.toTransformList(itemRequestPatch, Item.class);
        return itemGenericMapper.toTransformList(itemService.atualizarObservacao(itens), ItemResponse.class);
    }
}