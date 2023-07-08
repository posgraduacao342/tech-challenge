package api.techchallenge.infrastructure.db.postgres.adapters;

import api.techchallenge.application.mappers.PedidoMapper;
import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.out.PedidoAdapterPort;
import api.techchallenge.infrastructure.db.postgres.entity.PedidoEntity;
import api.techchallenge.infrastructure.db.postgres.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Component
@AllArgsConstructor
public class PedidoAdapter implements PedidoAdapterPort {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<Pedido> buscarPedidos() {
        var pedidosEntity = this.pedidoRepository.findAll();
        List<Pedido> pedidos = new ArrayList<Pedido>();

        for (PedidoEntity pedidoEntity : pedidosEntity) {
            pedidos.add(pedidoMapper.toDomain(pedidoEntity));
        }
        return pedidos;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public Optional<Pedido> buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException {
        var pedidoEntity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));

        return Optional.of(pedidoMapper.toDomain(pedidoEntity));
    }

    @Transactional
    @Override
    public Pedido salvarPedido(Pedido pedido) {
        var pedidoEntity = pedidoMapper.toEntity(pedido);
        return pedidoMapper.toDomain(pedidoRepository.save(pedidoEntity));
    }

    @Transactional
    @Override
    public void deletarPedido(UUID id) {
        this.pedidoRepository.deleteById(id);
    }
}
