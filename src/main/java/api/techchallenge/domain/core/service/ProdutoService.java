package api.techchallenge.domain.core.service;

import api.techchallenge.application.mappers.produto.ProdutoRequestParaProdutoMapper;
import api.techchallenge.application.requests.produto.AtualizarProdutoRequest;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.core.enums.Categoria;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.ProdutoServicePort;
import api.techchallenge.domain.ports.out.ProdutoAdapterPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;

public class ProdutoService implements ProdutoServicePort {
    private final ProdutoAdapterPort produtoAdapterPort;

    @Autowired
    private final ProdutoRequestParaProdutoMapper produtoRequestParaProdutoMapper;

    public ProdutoService(ProdutoAdapterPort produtoAdapterPort, ProdutoRequestParaProdutoMapper produtoRequestParaProdutoMapper) {
        this.produtoAdapterPort = produtoAdapterPort;
        this.produtoRequestParaProdutoMapper = produtoRequestParaProdutoMapper;
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
    public Produto atualizarProduto(UUID id, AtualizarProdutoRequest request){
        var produto = this.buscarProdutoPorId(id);

        produtoRequestParaProdutoMapper.convert(request, produto);

        return this.produtoAdapterPort.salvarProduto(produto);
    }

    @Override
    public void deletarProduto(UUID id){
        this.produtoAdapterPort.deletarProduto(id);
    }

    @Override
    public Produto criarNovoProduto(Produto produto){
        return this.produtoAdapterPort.salvarProduto(produto);
    }
}
