package api.techchallenge.domain.useCases;

import api.techchallenge.domain.entities.Pedido;
import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PagamentoUseCasesPort;
import api.techchallenge.domain.ports.in.PedidoUseCasesPort;
import api.techchallenge.domain.ports.out.PagamentoQrcodeGatewayPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class PagamentoUseCases implements PagamentoUseCasesPort {
    private final PedidoUseCasesPort pedidoUseCasesPort;
    private final PagamentoQrcodeGatewayPort pagamentoQrcodeGatewayPort;

    @Override
    public String atualizarStatusPagamento(UUID pedidoId, StatusPagamento statusPagamento) throws RecursoNaoEncontratoException {
        var pedido = this.pedidoUseCasesPort.buscarPedidoPorId(pedidoId);
        if(pedido.pagamentoRealizado()){
            return "O pagamento já foi realizado com sucesso.";
        }

        pedido.atualizarStatusPedido(statusPagamento);
        this.pedidoUseCasesPort.salvarPedido(pedido);

        return "Pagamento finalizado com sucesso";
    }

    @Override
    public String gerarQrcode(UUID pedidoId) throws RecursoNaoEncontratoException {
        Pedido pedido = pedidoUseCasesPort.buscarPedidoPorId(pedidoId);
        if(pedido.pagamentoRealizado()){
            return "O pagamento já foi realizado com sucesso.";
        }
        return pagamentoQrcodeGatewayPort.gerarQrcode(pedido);
    }

    @Override
    public void validarPagamento(String pagamentoId) throws RecursoNaoEncontratoException {
        var infoPagamento = pagamentoQrcodeGatewayPort.pagamentoComSucesso(pagamentoId);

        if(infoPagamento.getFoiPago()) {
            this.atualizarStatusPagamento(UUID.fromString(infoPagamento.getPedidoId()), StatusPagamento.PAGO);
        }
        this.atualizarStatusPagamento(UUID.fromString(infoPagamento.getPedidoId()), StatusPagamento.FRACASSADO);
    }

    @Override
    public String buscarStatusPagamentoPorPedidoId(UUID pedidoId) throws RecursoNaoEncontratoException{
        var pedido = this.pedidoUseCasesPort.buscarPedidoPorId(pedidoId);
        return pedido.getStatusPagamento().name();
    }
}
