package api.techchallenge.application.requests.pedido;

import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CriarPedidoRequest {

    private StatusPedido statusPedido;

    private StatusPagamento statusPagamento;

    private BigDecimal preco;
}
