package api.techchallenge.domain.core.service;

import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.ports.in.ProdutoServicePort;
import api.techchallenge.domain.ports.out.ProdutoAdapterPort;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.UUID;

public class ProdutoService implements ProdutoServicePort {
    private final ProdutoAdapterPort produtoAdapterPort;

    public ProdutoService(ProdutoAdapterPort produtoAdapterPort) {
        this.produtoAdapterPort = produtoAdapterPort;
    }

    @Override
    public List<Produto> buscarProdutos(){
        throw new NotImplementedException();
    }
    
    @Override
    public Produto buscarProdutoPorId(UUID id) {
        throw new NotImplementedException();
    }
    
    @Override
    public void deletarProduto(UUID id){
        throw new NotImplementedException();
    }

    @Override
    public Produto salvarProduto(Produto produto){
        return this.produtoAdapterPort.salvarProduto(produto);
    };
}
