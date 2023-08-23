package api.techchallenge.application.presenters.requests.pagamento;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PagarPedidoRequest {
    @NotBlank
    private String pedidoId;
}
