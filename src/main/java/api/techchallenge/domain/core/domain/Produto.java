package api.techchallenge.domain.core.domain;

import api.techchallenge.domain.core.enums.CategoriaEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Produto extends BaseEntity {
    public UUID id;
    public String nome;
    public BigDecimal preco;
    public String imagem;
    public String descricao;
    public CategoriaEnum categoria;
}