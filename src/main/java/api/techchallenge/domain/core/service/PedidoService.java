package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PedidoServicePort;
import api.techchallenge.domain.ports.out.PedidoAdapterPort;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public List<Pedido> buscarPedidos() {
        return this.pedidoAdapterPort.buscarPedidos();
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
        pedido.setStatusPedido(StatusPedido.NAO_RECEBIDO);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);
        return this.pedidoAdapterPort.salvarPedido(pedido);
    }

    @Override
    public Pedido atualizarPedido(Optional<Pedido> pedidoOptional, UUID id) {
        var pedido = this.buscarPedidoPorId(id);

        if (pedidoOptional.get().getStatusPedido() != null) {
            pedido.setStatusPedido(pedidoOptional.get().getStatusPedido());
        }
        if (pedidoOptional.get().getItens() != null) {
            pedido.setItens(pedidoOptional.get().getItens());
        }
        if (pedidoOptional.get().getPreco() != null) {
            pedido.setPreco(pedidoOptional.get().getPreco());
        }
        if (pedidoOptional.get().getStatusPagamento() != null) {
            pedido.setStatusPagamento(pedidoOptional.get().getStatusPagamento());
        }
        pedido.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

        return this.pedidoAdapterPort.salvarPedido(pedido);
    }

    @Override
    public void deletarPedido(UUID id) {
        this.pedidoAdapterPort.deletarPedido(id);
    }
}
