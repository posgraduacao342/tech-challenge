package api.techchallenge.application.presenters.responses.cliente;

import api.techchallenge.domain.entities.Pedido;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
public class CriarClienteResponse {
    private LocalDateTime dataCriacao;
    private LocalDateTime dataDelecao;
    private LocalDateTime dataAtualizacao;
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private List<Pedido> pedidos;
}