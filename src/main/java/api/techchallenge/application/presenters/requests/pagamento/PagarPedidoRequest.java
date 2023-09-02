package api.techchallenge.application.presenters.requests.pagamento;

import api.techchallenge.domain.enums.StatusPagamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PagarPedidoRequest {
    @NotBlank
    private String pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
}
