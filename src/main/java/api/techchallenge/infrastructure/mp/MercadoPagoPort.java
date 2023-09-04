package api.techchallenge.infrastructure.mp;

public interface MercadoPagoPort {
    String gerarQrcode(PedidoMP body);
    StatusPagamentoReponse buscarStatusPagamento(String pagamentoId);
    String obterStatusPgSucesso();
}
