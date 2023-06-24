package api.techchallenge.infrastructure.db.postgres.adapters;


import api.techchallenge.application.mappers.produto.ProdutoEntityParaProdutoMapper;
import api.techchallenge.application.mappers.produto.ProdutoParaProdutoEntityMapper;
import api.techchallenge.domain.core.domain.Produto;
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

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarProdutos() {
        var ProdutosEntity = this.produtoRepository.findAll();
        List<Produto> Produtos = new ArrayList<Produto>();

        for (ProdutoEntity ProdutoEntity: ProdutosEntity) {
            Produtos.add(this.produtoEntityParaProdutoMapper.mapper(ProdutoEntity));
        }
        return Produtos;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Produto> buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException {
        var ProdutoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));

        return Optional.of(this.produtoEntityParaProdutoMapper.mapper(ProdutoEntity));
    }

    @Transactional
    @Override
    public void deletarProduto(UUID id) {
        this.produtoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Produto salvarProduto(Produto Produto) {
        var ProdutoEntity = this.produtoParaProdutoEntityMapper.mapper(Produto);
        return produtoEntityParaProdutoMapper.mapper(produtoRepository.save(ProdutoEntity));
    }
}
