package api.techchallenge.domain.useCases;

import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.enums.StatusPedido;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PagamentoUseCasesPort;
import api.techchallenge.domain.ports.in.PedidoUseCasesPort;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class PagamentoUseCases implements PagamentoUseCasesPort {

    private final PedidoUseCasesPort pedidoUseCasesPort;

    public PagamentoUseCases(PedidoUseCasesPort pedidoUseCasesPort) {
        this.pedidoUseCasesPort = pedidoUseCasesPort;
    }
    @Override
    public String pagarPedido(UUID pedidoId) throws RecursoNaoEncontratoException {
        var pedido = this.pedidoUseCasesPort.buscarPedidoPorId(pedidoId);
        if(pedido.getStatusPagamento() == StatusPagamento.PAGO ){
            return "O pagamento já foi realizado com sucesso.";
        }

        pedido.setStatusPagamento(StatusPagamento.PAGO);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);
        pedido.setDataRecebimento(LocalDateTime.now(ZoneId.of("UTC")));

        this.pedidoUseCasesPort.salvarPedido(pedido);
        return "Pagamento finalizado com sucesso";
    }

    @Override
    public String buscarStatusPagamentoPorPedidoId(UUID pedidoId) throws RecursoNaoEncontratoException{
        var pedido = this.pedidoUseCasesPort.buscarPedidoPorId(pedidoId);
        return pedido.getStatusPagamento().name();
    }
}
