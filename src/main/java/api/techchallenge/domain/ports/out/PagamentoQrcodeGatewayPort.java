package api.techchallenge.domain.ports.out;

import api.techchallenge.application.presenters.responses.pagamento.InformacaoPagamentoResponse;
import api.techchallenge.domain.entities.Pedido;

public interface PagamentoQrcodeGatewayPort {
    String gerarQrcode(Pedido pedido);
    InformacaoPagamentoResponse pagamentoComSucesso(String pagamentoId);
}
