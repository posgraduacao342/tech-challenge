package api.techchallenge.infrastructure.db.entity;

import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.enums.StatusPedido;
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

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id=" + super.getId() +
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
