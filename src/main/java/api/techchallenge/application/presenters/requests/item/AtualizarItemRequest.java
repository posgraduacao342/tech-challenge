package api.techchallenge.application.presenters.requests.item;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class AtualizarItemRequest {
    @Nullable
    private String observacoes;
    @Nullable
    private Integer quantidade;
}
