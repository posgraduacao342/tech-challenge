package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.enums.StatusPagamento;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;

import java.util.UUID;

public interface PagamentoUseCasesPort {
    String atualizarStatusPagamento(UUID pedidoId, StatusPagamento statusPagamento) throws RecursoNaoEncontratoException;
    String gerarQrcode(UUID pedidoId) throws RecursoNaoEncontratoException;
    void validarPagamento(String pedidoId) throws RecursoNaoEncontratoException;
    String buscarStatusPagamentoPorPedidoId(UUID pedidoId) throws RecursoNaoEncontratoException;
}
