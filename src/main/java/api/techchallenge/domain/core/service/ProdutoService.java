package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.core.enums.Categoria;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.ProdutoServicePort;
import api.techchallenge.domain.ports.out.ProdutoAdapterPort;

import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;

public class ProdutoService implements ProdutoServicePort {
    private final ProdutoAdapterPort produtoAdapterPort;

    public ProdutoService(ProdutoAdapterPort produtoAdapterPort) {
        this.produtoAdapterPort = produtoAdapterPort;
    }

    @Override
    public List<Produto> buscarProdutos(){
        return this.produtoAdapterPort.buscarProdutos();
    }
    
    @Override
    public Produto buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException {
        return produtoAdapterPort.buscarProdutoPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(Categoria categoria) {
        return produtoAdapterPort.buscarProdutosPorCategoria(categoria);
    }
    
    @Override
    public void deletarProduto(UUID id){
        this.produtoAdapterPort.deletarProduto(id);
    }

    @Override
    public Produto salvarProduto(Produto produto){
        return this.produtoAdapterPort.salvarProduto(produto);
    };
}
