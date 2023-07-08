package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.PedidoMapper;
import api.techchallenge.application.requests.pedido.AtualizarPedidoRequest;
import api.techchallenge.application.requests.pedido.CriarPedidoRequest;
import api.techchallenge.application.responses.pedido.PedidoResponse;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.ports.in.PedidoServicePort;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Pedido> buscarPedidos() {
        return this.pedidoServicePort.buscarPedidos();
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

    @PatchMapping(value = "/{pedidoId}")
    public Pedido atualizarPedido(@PathVariable(value = "pedidoId") String pedidoId,
                                  @RequestBody @Valid AtualizarPedidoRequest atualizarPedidoRequest)
            throws UserPrincipalNotFoundException {
        var pedido = new Pedido();
        BeanUtils.copyProperties(atualizarPedidoRequest, pedido);
        return this.pedidoServicePort.atualizarPedido(Optional.of(pedido), UUID.fromString(pedidoId));
    }

    @DeleteMapping(value = "/{pedidoId}")
    public void deletar(@PathVariable(value = "pedidoId") String pedidoId) {
        this.pedidoServicePort.deletarPedido(UUID.fromString(pedidoId));
    }

}
