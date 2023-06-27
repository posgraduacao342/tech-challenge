package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoServicePort {
    List<Pedido> buscarPedidos();

    Pedido buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException;

    Pedido salvarPedido(Pedido pedido);

    Pedido atualizarPedido(Optional<Pedido> pedidoOptional, UUID id) throws RecursoNaoEncontratoException;

    void deletarPedido(UUID id);
}
