package api.techchallenge.infrastructure.db.postgres.entity;

import api.techchallenge.domain.core.enums.CategoriaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "produtos")
public class ProdutoEntity extends BaseEntity{
    @Column(nullable = false)
    public String nome;
    @Column(nullable = false)
    public BigDecimal preco;
    public String imagem;
    public String descricao;
    public CategoriaEnum categoria;
}
