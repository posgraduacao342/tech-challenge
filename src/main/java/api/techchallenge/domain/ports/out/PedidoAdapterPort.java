package api.techchallenge.domain.ports.out;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoAdapterPort {
    List<Pedido> buscarPedidos();

    Optional<Pedido> buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException;

    Pedido salvarPedido(Pedido pedido);

    void deletarPedido(UUID id);
}
