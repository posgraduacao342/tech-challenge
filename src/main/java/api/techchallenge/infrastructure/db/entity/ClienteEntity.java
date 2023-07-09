package api.techchallenge.infrastructure.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "clientes")
public class ClienteEntity extends BaseEntity {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidos;
}
