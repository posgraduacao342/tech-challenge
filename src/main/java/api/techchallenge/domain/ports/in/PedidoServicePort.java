package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.enums.PedidoSortingOptions;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoServicePort {
    List<Pedido> buscarPedidos(Optional<PedidoSortingOptions> sortingProperty, Optional<Sort.Direction> direction);

    List<Pedido> buscarFilaDePedidos();

    Pedido buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException;

    Pedido salvarPedido(Pedido pedido);

    Pedido atualizarPedido(Optional<Pedido> pedidoOptional, UUID id) throws RecursoNaoEncontratoException;

    void deletarPedido(UUID id);
}
