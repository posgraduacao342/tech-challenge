package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.GenericMapper;
import api.techchallenge.application.mappers.PedidoMapper;
import api.techchallenge.application.requests.pedido.AtualizarPedidoRequest;
import api.techchallenge.application.requests.pedido.CriarPedidoRequest;
import api.techchallenge.application.responses.pedido.PedidoResponse;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.enums.PedidoSortingOptions;
import api.techchallenge.domain.ports.in.PedidoServicePort;

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

    private final PedidoServicePort pedidoServicePort;
    private final PedidoMapper pedidoMapper;
    private final GenericMapper pedidoGMapper;

    @GetMapping
    public List<Pedido> buscarPedidos(Optional<PedidoSortingOptions> sortingProperty, Optional<Sort.Direction> direction) {
        return this.pedidoServicePort.buscarPedidos(sortingProperty, direction);
    }

    @GetMapping(value = "/fila")
    public List<Pedido> buscarFilaDePedidos() {
        return this.pedidoServicePort.buscarFilaDePedidos();
    }

    @GetMapping(value = "/{pedidoId}")
    public Pedido buscarPedidoPorId(@PathVariable(value = "pedidoId") String pedidoId) throws UserPrincipalNotFoundException {
        return this.pedidoServicePort.buscarPedidoPorId(UUID.fromString(pedidoId));
    }

    @PostMapping
    public PedidoResponse salvarPedido(@RequestBody @Valid CriarPedidoRequest pedidoRequest) {
        var pedido = pedidoMapper.toDomain(pedidoRequest);
        return pedidoMapper.toResponse(this.pedidoServicePort.salvarPedido(pedido));
    }

    @PatchMapping(value = "/{pedidoId}/status")
    public PedidoResponse atualizarPedido(@PathVariable(value = "pedidoId") String pedidoId,
                                  @RequestBody @Valid AtualizarPedidoRequest atualizarPedidoRequest)
            throws UserPrincipalNotFoundException {
        return pedidoMapper.toResponse(this.pedidoServicePort.atualizarStatusPedido(atualizarPedidoRequest.getStatusPedido(), UUID.fromString(pedidoId)));
    }

    @DeleteMapping(value = "/{pedidoId}")
    public void deletar(@PathVariable(value = "pedidoId") String pedidoId) {
        this.pedidoServicePort.deletarPedido(UUID.fromString(pedidoId));
    }

}
