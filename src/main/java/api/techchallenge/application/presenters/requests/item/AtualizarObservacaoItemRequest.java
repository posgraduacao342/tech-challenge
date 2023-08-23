package api.techchallenge.application.presenters.requests.item;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AtualizarObservacaoItemRequest {

    @NotBlank
    private UUID id;

    @NotBlank
    private String observacoes;

}
