package api.techchallenge.domain.core.domain;

import api.techchallenge.domain.core.enums.StatusPagamento;
import api.techchallenge.domain.core.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Pedido extends BaseEntity {
    private StatusPedido statusPedido;
    private BigDecimal preco;
    private StatusPagamento statusPagamento;
    private Set<Item> itens;
}
