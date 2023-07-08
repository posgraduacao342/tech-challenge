package api.techchallenge.application.requests.pedido;

import api.techchallenge.domain.core.enums.StatusPedido;
import lombok.Data;
import org.springframework.lang.Nullable;


@Data
public class AtualizarPedidoRequest {

    @Nullable
    private StatusPedido statusPedido;
}
