package api.techchallenge.domain.ports.in;

import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.UUID;

public interface ProdutoServicePort {
    
    List<Produto> buscarProdutos();

    Produto buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException;

    void deletarProduto(UUID id);

    Produto salvarProduto(Produto Produto);
}
