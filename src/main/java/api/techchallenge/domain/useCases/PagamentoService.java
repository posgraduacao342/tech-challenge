package api.techchallenge.domain.useCases;

import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.enums.StatusPedido;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PagamentoServicePort;
import api.techchallenge.domain.ports.in.PedidoServicePort;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class PagamentoService implements PagamentoServicePort {

    private final PedidoServicePort pedidoServicePort;

    public PagamentoService(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }
    @Override
    public String pagarPedido(UUID pedidoId) throws RecursoNaoEncontratoException {
        var pedido = this.pedidoServicePort.buscarPedidoPorId(pedidoId);
        if(pedido.getStatusPagamento() == StatusPagamento.PAGO ){
            return "O pagamento já foi realizado com sucesso.";
        }

        pedido.setStatusPagamento(StatusPagamento.PAGO);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);
        pedido.setDataRecebimento(LocalDateTime.now(ZoneId.of("UTC")));

        this.pedidoServicePort.salvarPedido(pedido);
        return "Pagamento finalizado com sucesso";
    }
}
