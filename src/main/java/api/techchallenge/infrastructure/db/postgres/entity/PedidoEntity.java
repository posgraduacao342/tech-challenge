package api.techchallenge.infrastructure.db.postgres.entity;

import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemEntity> itens;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column()
    private LocalDateTime dataRecebimento;

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id=" + super.getId() +
                // Adicione outros campos desejados...
                '}';
    }

    public PedidoEntity() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemEntity item) {
        var listaItem = this.getItens();
        listaItem.add(item);
        item.setPedido(this);
    }
}
