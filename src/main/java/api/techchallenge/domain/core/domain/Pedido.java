package api.techchallenge.domain.core.domain;

import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Pedido extends BaseEntity {
    private Cliente cliente;
    private StatusPedido statusPedido;
    private BigDecimal preco;
    private StatusPagamento statusPagamento;
    private List<Item> itens;
    private LocalDateTime dataRecebimento;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item){
        var listaItem = this.getItens();
        listaItem.add(item);
    }
}
