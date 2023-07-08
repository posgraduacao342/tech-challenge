package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.enums.PedidoSortingOptions;
import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PedidoServicePort;
import api.techchallenge.domain.ports.out.PedidoAdapterPort;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.text.MessageFormat.format;

public class PedidoService implements PedidoServicePort {

    private final PedidoAdapterPort pedidoAdapterPort;

    public PedidoService(PedidoAdapterPort pedidoAdapterPort) {
        this.pedidoAdapterPort = pedidoAdapterPort;
    }

    @Override
    public List<Pedido> buscarPedidos(Optional<PedidoSortingOptions> sortingProperty, Optional<Sort.Direction> direction) {
        return this.pedidoAdapterPort.buscarPedidos(sortingProperty, direction);
    }

    @Override
    public List<Pedido> buscarFilaDePedidos() {
        List<StatusPedido> statusPedidoList = new ArrayList<>();
        statusPedidoList.add(StatusPedido.RECEBIDO);
        statusPedidoList.add(StatusPedido.EM_PREPARACAO);

        return this.pedidoAdapterPort.buscarPedidosPorStatusPedido(statusPedidoList, PedidoSortingOptions.DATA_RECEBIMENTO, Sort.Direction.ASC);
    }

    @Override
    public Pedido buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException {
        return pedidoAdapterPort.buscarPedidoPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));
    }

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        pedido.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        pedido.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

        if (pedido.getStatusPagamento() == null) {
            pedido.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);
        }
        if (pedido.getStatusPedido() == null) {
            pedido.setStatusPedido(StatusPedido.NAO_RECEBIDO);
        }

        return this.pedidoAdapterPort.salvarPedido(pedido);
    }

    @Override
    public Pedido atualizarStatusPedido(StatusPedido statusPedido, UUID id) {
        var pedido = this.buscarPedidoPorId(id);
        pedido.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
        pedido.setStatusPedido(statusPedido);
        return this.pedidoAdapterPort.salvarPedido(pedido);
    }

    @Override
    public void deletarPedido(UUID id) {
        this.pedidoAdapterPort.deletarPedido(id);
    }
}
