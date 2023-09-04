package api.techchallenge.application.gateway;

import api.techchallenge.application.presenters.responses.pagamento.InformacaoPagamentoResponse;
import api.techchallenge.domain.entities.Pedido;
import api.techchallenge.domain.ports.out.PagamentoQrcodeGatewayPort;
import api.techchallenge.infrastructure.mp.ItemMP;
import api.techchallenge.infrastructure.mp.MercadoPagoPort;
import api.techchallenge.infrastructure.mp.PedidoMP;
import api.techchallenge.infrastructure.security.config.AppConfig;
import lombok.AllArgsConstructor;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PagamentoQrcodeGateway implements PagamentoQrcodeGatewayPort {
    private final MercadoPagoPort mercadoPago;
    private final AppConfig appConfig;

    @Override
    public String gerarQrcode(Pedido pedido) {

        var body = new PedidoMP(
            pedido.getId().toString(), 
            pedido.getPreco().doubleValue(),
            this.gerarDataDeExpiracao(), 
            appConfig.getUrlWebhook()
        );
        pedido.getItens().forEach(item -> {
            var produto = item.getProduto();
            ItemMP itemBody = new ItemMP(
                produto.getId().toString(),
                produto.getNome(), 
                item.getQuantidade(), 
                produto.getPreco().doubleValue(),
                item.calcularTotal().doubleValue()
            );
            body.adicionarItem(itemBody);
        });
   
        return mercadoPago.gerarQrcode(body);
    }

    @Override
    public InformacaoPagamentoResponse pagamentoComSucesso(String pagamentoId) {
        var response = mercadoPago.buscarStatusPagamento(pagamentoId);
        var infoPagamento = new InformacaoPagamentoResponse();

        infoPagamento.setPedidoId(response.external_reference);
        infoPagamento.setFoiPago(response.status.equals(mercadoPago.obterStatusPgSucesso()));
        return infoPagamento;
    }

    private String gerarDataDeExpiracao() {
        OffsetDateTime currentDateTime = OffsetDateTime.now();
        OffsetDateTime expirationDateTime = currentDateTime.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String expirationDateStr = formatter.format(expirationDateTime);

        return expirationDateStr;
    }
    
}
