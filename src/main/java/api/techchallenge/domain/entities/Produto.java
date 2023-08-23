package api.techchallenge.domain.entities;

import api.techchallenge.domain.enums.Categoria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Produto extends BaseEntity {
    private String nome;
    private BigDecimal preco;
    private String imagem;
    private String descricao;
    private Categoria categoria;
}