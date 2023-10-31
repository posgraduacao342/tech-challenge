package api.techchallenge.application.presenters.requests.pedido;

import api.techchallenge.domain.enums.StatusPedido;
import lombok.Data;
import org.springframework.lang.Nullable;


@Data
public class AtualizarPedidoRequest {

    @Nullable
    private StatusPedido statusPedido;
}
