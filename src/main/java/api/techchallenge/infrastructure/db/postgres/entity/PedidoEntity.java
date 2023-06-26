package api.techchallenge.infrastructure.db.postgres.entity;

import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private BigDecimal preco;

    @Column()
    private LocalDateTime dataRecebimento;
}
