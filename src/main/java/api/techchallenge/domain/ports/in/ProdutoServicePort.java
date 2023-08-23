package api.techchallenge.domain.ports.in;

import api.techchallenge.application.presenters.requests.produto.AtualizarProdutoRequest;
import api.techchallenge.domain.entities.Produto;
import api.techchallenge.domain.enums.Categoria;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;

import java.util.List;
import java.util.UUID;

public interface ProdutoServicePort {
    
    List<Produto> buscarProdutos();

    Produto buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException;

    List<Produto> buscarProdutosPorCategoria(Categoria categoria);

    Produto atualizarProduto(UUID id, AtualizarProdutoRequest produto);

    void deletarProduto(UUID id);

    Produto criarNovoProduto(Produto Produto);
}
