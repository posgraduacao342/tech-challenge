package api.techchallenge.domain.useCases;

import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PagamentoServicePort;
import api.techchallenge.domain.ports.in.PedidoServicePort;

import java.util.UUID;

public class PagamentoService implements PagamentoServicePort {

    private final PedidoServicePort pedidoServicePort;

    public PagamentoService(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }
    @Override
    public String pagarPedido(UUID pedidoId, StatusPagamento statusPagamento) throws RecursoNaoEncontratoException {
        var pedido = this.pedidoServicePort.buscarPedidoPorId(pedidoId);
        if(pedido.pagamentoRealizado()){
            return "O pagamento já foi realizado com sucesso.";
        }

        pedido.atualizarStatusPedido(statusPagamento);
        this.pedidoServicePort.salvarPedido(pedido);

        return "Pagamento finalizado com sucesso";
    }
}
