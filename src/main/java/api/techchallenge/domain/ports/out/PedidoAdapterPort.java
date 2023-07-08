package api.techchallenge.domain.ports.out;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.enums.PedidoSortingOptions;
import api.techchallenge.domain.core.enums.StatusPedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoAdapterPort {
    List<Pedido> buscarPedidos(Optional<PedidoSortingOptions> sortingProperty, Optional<Sort.Direction> direction);

    List<Pedido> buscarPedidosPorStatusPedido(List<StatusPedido> statusPedidoList, PedidoSortingOptions sortingProperty, Sort.Direction direction);

    Optional<Pedido> buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException;

    Pedido salvarPedido(Pedido pedido);

    void deletarPedido(UUID id);
}
