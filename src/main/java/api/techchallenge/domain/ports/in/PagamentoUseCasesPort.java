package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.exception.RecursoNaoEncontratoException;

import java.util.UUID;

public interface PagamentoUseCasesPort {
    String pagarPedido(UUID pedidoId) throws RecursoNaoEncontratoException;
    String buscarStatusPagamentoPorPedidoId(UUID pedidoId) throws RecursoNaoEncontratoException;
}
