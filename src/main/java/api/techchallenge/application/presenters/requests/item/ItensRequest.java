package api.techchallenge.application.presenters.requests.item;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;
@Data
public class ItensRequest {
    @NotBlank
    private UUID produtoId;

    private String observacoes;
    @NotBlank
    private int quantidade;
}
