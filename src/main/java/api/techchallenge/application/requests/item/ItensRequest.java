package api.techchallenge.application.requests.item;

import lombok.Data;

import java.util.UUID;
@Data
public class ItensRequest {
    private UUID produtoId;
    private String observacoes;
    private int quantidade;
}
