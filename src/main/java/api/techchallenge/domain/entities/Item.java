package api.techchallenge.domain.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Item {
    private UUID id;
    private Produto produto;
    private Pedido pedido;
    private String observacoes;
    private int quantidade;
}