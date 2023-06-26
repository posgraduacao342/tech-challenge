package api.techchallenge.infrastructure.db.postgres.adapters;

import api.techchallenge.application.mappers.PedidoEntityParaPedidoMapper;
import api.techchallenge.application.mappers.PedidoParaPedidoEntityMapper;
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

    private final PedidoEntityParaPedidoMapper pedidoEntityParaPedidoMapper;

    private final PedidoParaPedidoEntityMapper pedidoParaPedidoEntityMapper;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<Pedido> buscarPedidos() {
        var pedidosEntity = this.pedidoRepository.findAll();
        List<Pedido> pedidos = new ArrayList<Pedido>();

        for (PedidoEntity pedidoEntity : pedidosEntity) {
            pedidos.add(this.pedidoEntityParaPedidoMapper.mapper(pedidoEntity));
        }
        return pedidos;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public Optional<Pedido> buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException {
        var pedidoEntity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));

        return Optional.of(this.pedidoEntityParaPedidoMapper.mapper(pedidoEntity));
    }

    @Transactional
    @Override
    public Pedido salvarPedido(Pedido pedido) {
        var pedidoEntity = this.pedidoParaPedidoEntityMapper.mapper(pedido);
        return pedidoEntityParaPedidoMapper.mapper(pedidoRepository.save(pedidoEntity));
    }

    @Transactional
    @Override
    public void deletarPedido(UUID id) {
        this.pedidoRepository.deleteById(id);
    }
}
