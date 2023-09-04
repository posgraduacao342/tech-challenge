package api.techchallenge.infrastructure.mp;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import api.techchallenge.infrastructure.security.config.AppConfig;
import reactor.core.publisher.Mono;

@Service
public class MercadoPagoService extends HttpBase implements MercadoPagoPort {
    private final String qrcodeUri = "/instore/orders/qr/seller/collectors/1467136605/pos/test123456/qrs";
    private final String statusPagamento = "/v1/payments/{pagamentoId}";
    public final String statusPago = "approved";
    private final AppConfig appConfig;

    public MercadoPagoService(WebClient.Builder webClientBuilder, AppConfig appConfig) {
        super(webClientBuilder, "https://api.mercadopago.com");
        this.appConfig = appConfig;
    }

    public String gerarQrcode(PedidoMP body) {
        var data = Mono.just(body);

        var response = webClient
            .post()
            .uri(qrcodeUri)
            .body(data, PedidoMP.class)
            .header("Authorization", appConfig.getMPToken())
            .retrieve()
            .bodyToMono(QrcodeResponse.class)
            .onErrorResume(error -> Mono.error(new Exception("Erro ao gerar o QRCODE", error)))
            .block();
        
        return response.qr_data;
    }

    public StatusPagamentoReponse buscarStatusPagamento(String pagamentoId) {
        var response =  webClient
            .get()
            .uri(statusPagamento, pagamentoId)
            .header("Authorization", appConfig.getMPToken())
            .retrieve()
            .bodyToMono(StatusPagamentoReponse.class)
            .onErrorResume(error -> (
                    Mono.error(new Exception("Erro ao buscar o status do pagamento", error))
            ))
            .block();
        
        return response;
    }

    public String obterStatusPgSucesso() {
        return statusPago;
    }
}
