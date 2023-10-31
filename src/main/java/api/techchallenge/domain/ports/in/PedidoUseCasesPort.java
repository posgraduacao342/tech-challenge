package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.entities.Pedido;
import api.techchallenge.domain.enums.PedidoSortingOptions;
import api.techchallenge.domain.enums.StatusPedido;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoUseCasesPort {
    List<Pedido> buscarPedidos(Optional<PedidoSortingOptions> sortingProperty, Optional<Sort.Direction> direction);

    List<Pedido> buscarFilaDePedidos();

    Pedido buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException;

    Pedido salvarPedido(Pedido pedido);

    Pedido atualizarStatusPedido(StatusPedido statusPedido, UUID id) throws RecursoNaoEncontratoException;

    void deletarPedido(UUID id);
}
