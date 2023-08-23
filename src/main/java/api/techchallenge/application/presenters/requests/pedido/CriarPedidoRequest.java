package api.techchallenge.application.presenters.requests.pedido;

import api.techchallenge.application.presenters.requests.item.ItensRequest;
import lombok.Data;

import java.math.BigDecimal;

import java.util.List;
import java.util.UUID;

@Data
public class CriarPedidoRequest {

    private UUID idCliente;

    private BigDecimal preco;

    private List<ItensRequest> itens;
}
