package api.techchallenge.domain.ports.in;

import api.techchallenge.application.requests.produto.AtualizarProdutoRequest;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.core.enums.Categoria;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.UUID;

public interface PagamentoServicePort {

    String pagarPedido(UUID idPedido) throws RecursoNaoEncontratoException;;
}
