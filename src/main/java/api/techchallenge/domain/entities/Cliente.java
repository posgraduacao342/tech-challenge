package api.techchallenge.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cliente extends BaseEntity {
    private String nome;
    private String email;
    private String cpf;
    private List<Pedido> pedidos;
}
