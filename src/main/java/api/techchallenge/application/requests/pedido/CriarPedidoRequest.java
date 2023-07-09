package api.techchallenge.application.requests.pedido;

import api.techchallenge.application.requests.item.ItensRequest;
import lombok.Data;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class CriarPedidoRequest {

    private UUID idCliente;

    private BigDecimal preco;

    private List<ItensRequest> itens;
}
