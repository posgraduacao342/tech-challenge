package api.techchallenge.infrastructure.db.postgres.entity;

import api.techchallenge.domain.core.enums.Categoria;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    public Categoria categoria;
}
