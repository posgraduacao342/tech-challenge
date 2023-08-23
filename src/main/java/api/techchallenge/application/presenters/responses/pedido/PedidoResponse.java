package api.techchallenge.application.presenters.responses.pedido;

import api.techchallenge.application.presenters.responses.cliente.ClienteResponse;
import api.techchallenge.application.presenters.responses.item.ItemResponse;
import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.enums.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PedidoResponse {

    private UUID id;
    private ClienteResponse cliente;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataDelecao;
    private LocalDateTime dataAtualizacao;
    private StatusPedido statusPedido;
    private BigDecimal preco;
    private StatusPagamento statusPagamento;
    private List<ItemResponse> itens;

    public PedidoResponse() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemResponse item) {
        var listaItem = this.getItens();
        listaItem.add(item);
    }
}
