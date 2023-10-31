package api.techchallenge.domain.useCases;

import api.techchallenge.application.presenters.mappers.ProdutoMapper;
import api.techchallenge.application.presenters.requests.produto.AtualizarProdutoRequest;
import api.techchallenge.domain.entities.Produto;
import api.techchallenge.domain.enums.Categoria;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.in.ProdutoUseCasesPort;
import api.techchallenge.domain.ports.out.ProdutoAdapterPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;

@AllArgsConstructor
public class ProdutoUseCases implements ProdutoUseCasesPort {
    private final ProdutoAdapterPort produtoAdapterPort;
    private final ProdutoMapper produtoMapper;

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

        produtoMapper.toDomain(request, produto);

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
