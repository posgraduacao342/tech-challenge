package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PagamentoServicePort;
import api.techchallenge.domain.ports.in.PedidoServicePort;

import java.util.Optional;
import java.util.UUID;

public class PagamentoService implements PagamentoServicePort {

    private final PedidoServicePort pedidoServicePort;

    public PagamentoService(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @Override
    public String pagarPedido(UUID idPedido) throws RecursoNaoEncontratoException {
        var pedido = new Pedido();
        pedido.setStatusPagamento(StatusPagamento.PAGO);
        this.pedidoServicePort.atualizarPedido(Optional.of(pedido), idPedido);
        return "Pagamento finalizado com sucesso";
    }
}
