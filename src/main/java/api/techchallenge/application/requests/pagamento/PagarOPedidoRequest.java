package api.techchallenge.application.requests.pagamento;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagarOPedidoRequest {
    @NotBlank
    private String idPedido;
}
