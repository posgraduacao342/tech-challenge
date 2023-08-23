package api.techchallenge.domain.ports.out;

import api.techchallenge.domain.entities.Produto;
import api.techchallenge.domain.enums.Categoria;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoAdapterPort {
    List<Produto> buscarProdutos();

    Optional<Produto> buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException;

    List<Produto> buscarProdutosPorCategoria(Categoria categoria);

    void deletarProduto(UUID id);

    Produto salvarProduto(Produto Produto);
}
