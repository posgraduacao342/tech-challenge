package api.techchallenge.domain.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BaseEntity {
    private UUID id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataDelecao;
    private LocalDateTime dataAtualizacao;
}
