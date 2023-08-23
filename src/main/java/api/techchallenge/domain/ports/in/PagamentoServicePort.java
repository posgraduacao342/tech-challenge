package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.exception.RecursoNaoEncontratoException;

import java.util.UUID;

public interface PagamentoServicePort {
    String pagarPedido(UUID pedidoId) throws RecursoNaoEncontratoException;
}
