package api.techchallenge.infrastructure.db.postgres.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "itens")
@Data
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "quantidade")
    private int quantidade;
}
