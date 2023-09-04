package api.techchallenge.application.presenters.responses.pagamento;

import lombok.Data;

@Data
public class InformacaoPagamentoResponse {
    public Boolean foiPago;
    public String pedidoId;
}
