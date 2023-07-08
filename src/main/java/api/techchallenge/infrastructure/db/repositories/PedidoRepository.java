package api.techchallenge.infrastructure.db.repositories;

import api.techchallenge.domain.core.enums.StatusPedido;
import api.techchallenge.infrastructure.db.entity.PedidoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {
    List<PedidoEntity> findByStatusPedidoIn(List<StatusPedido> statusPedido, Sort sort);
    List<PedidoEntity> findByStatusPedidoIn(List<StatusPedido> statusPedido);
}
