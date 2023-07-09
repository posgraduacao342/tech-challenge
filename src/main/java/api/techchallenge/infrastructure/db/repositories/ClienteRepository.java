package api.techchallenge.infrastructure.db.repositories;

import api.techchallenge.domain.core.enums.StatusPedido;
import api.techchallenge.infrastructure.db.entity.ClienteEntity;
import api.techchallenge.infrastructure.db.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findByCpf(String cpf);
}
