package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.PedidoRequestParaPedidoMapper;
import api.techchallenge.application.requests.pedido.CriarPedidoRequest;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.ports.in.PedidoServicePort;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoServicePort pedidoServicePort;

    private final PedidoRequestParaPedidoMapper pedidoRequestParaPedidoMapper;

    @GetMapping
    public List<Pedido> buscarPedidos() {
        return this.pedidoServicePort.buscarPedidos();
    }

    @GetMapping(value = "/{pedidoId}")
    public Pedido buscarPedidoPorId(@PathVariable(value = "pedidoId") String pedidoId) throws UserPrincipalNotFoundException {
        return this.pedidoServicePort.buscarPedidoPorId(UUID.fromString(pedidoId));
    }

    @PostMapping
    public Pedido salvarPedido(@RequestBody @Valid CriarPedidoRequest pedidoRequest) {
        var pedido = pedidoRequestParaPedidoMapper.mapper(pedidoRequest);
        return this.pedidoServicePort.salvarPedido(pedido);
    }

    @DeleteMapping(value = "/{pedidoId}")
    public void deletar(@PathVariable(value = "pedidoId") String pedidoId) {
        this.pedidoServicePort.deletarPedido(UUID.fromString(pedidoId));
    }

}
