package api.techchallenge.domain.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEntity {
    private UUID id;
    private LocalDateTime dataCriacao;

    private LocalDateTime dataDelecao;
    private LocalDateTime dataAtualizacao;

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataDelecao() {
        return dataDelecao;
    }

    public void setDataDelecao(LocalDateTime dataDelecao) {
        this.dataDelecao = dataDelecao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}
