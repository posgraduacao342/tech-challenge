package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.ItemMapper;
import api.techchallenge.application.requests.item.AtualizarItemRequest;
import api.techchallenge.application.requests.item.CriarItemRequest;
import api.techchallenge.domain.core.domain.Item;
import api.techchallenge.domain.ports.in.ItemServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/itens")
public class ItemController {
    private final ItemServicePort itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemServicePort itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

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
}