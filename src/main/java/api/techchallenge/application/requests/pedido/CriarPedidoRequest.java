package api.techchallenge.application.requests.pedido;

import api.techchallenge.application.requests.item.ItensRequest;
import lombok.Data;

import java.math.BigDecimal;

import java.util.List;

@Data
public class CriarPedidoRequest {

    private BigDecimal preco;

    private List<ItensRequest> itens;
}
