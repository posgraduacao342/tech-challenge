package api.techchallenge.application.presenters.requests.pagamento;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GerarQrcodeRequest {
    @NotBlank
    private String pedidoId;
}
