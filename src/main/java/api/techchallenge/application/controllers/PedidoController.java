package api.techchallenge.application.controllers;

import api.techchallenge.application.presenters.mappers.PedidoMapper;
import api.techchallenge.application.presenters.requests.pedido.AtualizarPedidoRequest;
import api.techchallenge.application.presenters.requests.pedido.CriarPedidoRequest;
import api.techchallenge.application.presenters.responses.pedido.PedidoResponse;
import api.techchallenge.domain.entities.Pedido;
import api.techchallenge.domain.enums.PedidoSortingOptions;
import api.techchallenge.domain.ports.in.PedidoUseCasesPort;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoUseCasesPort pedidoUseCases;
    private final PedidoMapper pedidoMapper;

    @GetMapping
    public List<Pedido> buscarPedidos(Optional<PedidoSortingOptions> sortingProperty, Optional<Sort.Direction> direction) {
        return this.pedidoUseCases.buscarPedidos(sortingProperty, direction);
    }

    @GetMapping(value = "/fila")
    public List<Pedido> buscarFilaDePedidos() {
        return this.pedidoUseCases.buscarFilaDePedidos();
    }

    @GetMapping(value = "/{pedidoId}")
    public Pedido buscarPedidoPorId(@PathVariable(value = "pedidoId") String pedidoId) throws UserPrincipalNotFoundException {
        return this.pedidoUseCases.buscarPedidoPorId(UUID.fromString(pedidoId));
    }

    @PostMapping
    public PedidoResponse salvarPedido(@RequestBody @Valid CriarPedidoRequest pedidoRequest) {
        var pedido = pedidoMapper.toDomain(pedidoRequest);
        return pedidoMapper.toResponse(this.pedidoUseCases.salvarPedido(pedido));
    }

    @PatchMapping(value = "/{pedidoId}/status")
    public PedidoResponse atualizarPedido(@PathVariable(value = "pedidoId") String pedidoId,
                                  @RequestBody @Valid AtualizarPedidoRequest atualizarPedidoRequest)
            throws UserPrincipalNotFoundException {
        return pedidoMapper.toResponse(this.pedidoUseCases.atualizarStatusPedido(atualizarPedidoRequest.getStatusPedido(), UUID.fromString(pedidoId)));
    }

    @DeleteMapping(value = "/{pedidoId}")
    public void deletar(@PathVariable(value = "pedidoId") String pedidoId) {
        this.pedidoUseCases.deletarPedido(UUID.fromString(pedidoId));
    }

}
