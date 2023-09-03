package api.techchallenge.domain.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Item {
    private UUID id;
    private Produto produto;
    private Pedido pedido;
    private String observacoes;
    private int quantidade;

     public BigDecimal calcularTotal() {
        BigDecimal quantidadeDecimal = BigDecimal.valueOf(this.quantidade);
        BigDecimal valorUnitario = this.produto.getPreco();
        BigDecimal total = valorUnitario.multiply(quantidadeDecimal);

        return total;
    }
}