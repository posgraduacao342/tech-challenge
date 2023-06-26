package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Pedido;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.PedidoServicePort;
import api.techchallenge.domain.ports.out.PedidoAdapterPort;

import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;

public class PedidoService implements PedidoServicePort {

    private final PedidoAdapterPort pedidoAdapterPort;

    public PedidoService(PedidoAdapterPort pedidoAdapterPort) {
        this.pedidoAdapterPort = pedidoAdapterPort;
    }

    @Override
    public List<Pedido> buscarPedidos() {
        return this.pedidoAdapterPort.buscarPedidos();
    }

    @Override
    public Pedido buscarPedidoPorId(UUID id) throws RecursoNaoEncontratoException {
        return pedidoAdapterPort.buscarPedidoPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));
    }

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        return this.pedidoAdapterPort.salvarPedido(pedido);
    }

    @Override
    public void deletarPedido(UUID id) {
        this.pedidoAdapterPort.deletarPedido(id);
    }
}
