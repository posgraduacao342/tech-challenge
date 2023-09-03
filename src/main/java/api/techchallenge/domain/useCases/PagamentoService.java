package api.techchallenge.domain.useCases;

import api.techchallenge.domain.entities.Pedido;
import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PagamentoServicePort;
import api.techchallenge.domain.ports.in.PedidoServicePort;
import api.techchallenge.domain.ports.out.PagamentoQrcodeGatewayPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class PagamentoService implements PagamentoServicePort {
    private final PedidoServicePort pedidoServicePort;
    private final PagamentoQrcodeGatewayPort pagamentoQrcodeGatewayPort;

    @Override
    public String atualizarStatusPagamento(UUID pedidoId, StatusPagamento statusPagamento) throws RecursoNaoEncontratoException {
        var pedido = this.pedidoServicePort.buscarPedidoPorId(pedidoId);
        if(pedido.pagamentoRealizado()){
            return "O pagamento já foi realizado com sucesso.";
        }

        pedido.atualizarStatusPedido(statusPagamento);
        this.pedidoServicePort.salvarPedido(pedido);

        return "Pagamento finalizado com sucesso";
    }

    @Override
    public String gerarQrcode(UUID pedidoId) throws RecursoNaoEncontratoException {
        Pedido pedido = pedidoServicePort.buscarPedidoPorId(pedidoId);
        if(pedido.pagamentoRealizado()){
            return "O pagamento já foi realizado com sucesso.";
        }
        return pagamentoQrcodeGatewayPort.gerarQrcode(pedido);
    };

    @Override
    public void validarPagamento(String pagamentoId) throws RecursoNaoEncontratoException {
        var infoPagamento = pagamentoQrcodeGatewayPort.pagamentoComSucesso(pagamentoId);

        if(infoPagamento.getFoiPago()) {
            this.atualizarStatusPagamento(UUID.fromString(infoPagamento.getPedidoId()), StatusPagamento.PAGO);
        }
        this.atualizarStatusPagamento(UUID.fromString(infoPagamento.getPedidoId()), StatusPagamento.FRACASSADO);
    };
}
