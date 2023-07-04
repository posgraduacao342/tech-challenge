package api.techchallenge.infrastructure.db.postgres.entity;

import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pedidos")
public class PedidoEntity extends BaseEntity {
    @Column()
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column()
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @OneToMany(mappedBy = "pedido")
    private Set<ItemEntity> itens;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column()
    private LocalDateTime dataRecebimento;
}
