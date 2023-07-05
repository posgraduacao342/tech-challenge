package api.techchallenge.application.requests.pedido;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CriarPedidoRequest {

    private BigDecimal preco;
}
