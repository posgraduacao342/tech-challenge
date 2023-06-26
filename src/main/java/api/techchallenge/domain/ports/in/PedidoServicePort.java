package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.UUID;

public interface PedidoServicePort {
    List<Pedido> buscarPedidos();

    Pedido buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException;

    Pedido salvarPedido(Pedido pedido);

    void deletarPedido(UUID id);
}
