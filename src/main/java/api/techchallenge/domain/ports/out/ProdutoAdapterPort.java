package api.techchallenge.domain.ports.out;

import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoAdapterPort {
    List<Produto> buscarProdutos();

    Optional<Produto> buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException;

    void deletarProduto(UUID id);

    Produto salvarProduto(Produto Produto);
}
