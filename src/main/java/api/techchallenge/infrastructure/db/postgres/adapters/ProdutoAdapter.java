package api.techchallenge.infrastructure.db.postgres.adapters;


import api.techchallenge.application.mappers.produto.ProdutoEntityListParaProdutoListMapper;
import api.techchallenge.application.mappers.produto.ProdutoEntityParaProdutoMapper;
import api.techchallenge.application.mappers.produto.ProdutoParaProdutoEntityMapper;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.core.enums.Categoria;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.out.ProdutoAdapterPort;
import api.techchallenge.infrastructure.db.postgres.entity.ProdutoEntity;
import api.techchallenge.infrastructure.db.postgres.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.text.MessageFormat.format;

@Component
@AllArgsConstructor
public class ProdutoAdapter implements ProdutoAdapterPort {
    private final ProdutoRepository produtoRepository;

    private final ProdutoEntityParaProdutoMapper produtoEntityParaProdutoMapper;
    private final ProdutoParaProdutoEntityMapper produtoParaProdutoEntityMapper;
    private final ProdutoEntityListParaProdutoListMapper produtoEntityListParaProdutoListMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarProdutos() {
        var produtosEntity = this.produtoRepository.findAll();
        List<Produto> produtos = new ArrayList<>();

        for (ProdutoEntity ProdutoEntity: produtosEntity) {
            produtos.add(this.produtoEntityParaProdutoMapper.convert(ProdutoEntity));
        }
        return produtos;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Produto> buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException {
        var produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));

        return Optional.of(this.produtoEntityParaProdutoMapper.convert(produtoEntity));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarProdutosPorCategoria(Categoria categoria){
        var produtoEntityList = produtoRepository.findByCategoria(categoria);

        return this.produtoEntityListParaProdutoListMapper.convert(produtoEntityList);
    }

    @Transactional
    @Override
    public void deletarProduto(UUID id) {
        this.produtoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Produto salvarProduto(Produto Produto) {
        var ProdutoEntity = this.produtoParaProdutoEntityMapper.convert(Produto);
        return produtoEntityParaProdutoMapper.convert(produtoRepository.save(ProdutoEntity));
    }
}
