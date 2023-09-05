package api.techchallenge.application.gateway;


import api.techchallenge.application.presenters.mappers.ProdutoMapper;
import api.techchallenge.domain.entities.Produto;
import api.techchallenge.domain.enums.Categoria;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.ports.out.ProdutoAdapterPort;
import api.techchallenge.infrastructure.db.entity.ProdutoEntity;
import api.techchallenge.infrastructure.db.repositories.ProdutoRepository;
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
public class ProdutoGateway implements ProdutoAdapterPort {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarProdutos() {
        var produtosEntity = produtoRepository.findAll();
        List<Produto> produtos = new ArrayList<>();

        for (ProdutoEntity produtoEntity : produtosEntity) {
            produtos.add(produtoMapper.toDomain(produtoEntity));
        }

        return produtos;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Produto> buscarProdutoPorId(UUID id) throws RecursoNaoEncontratoException {
        var produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com código {0}", id)));

        return Optional.of(produtoMapper.toDomain(produtoEntity));
    }

    @Override
    public List<Produto> buscarProdutoPorIds(List<UUID> ids) {
        var produtoEntity = produtoRepository.findByIdIn(ids);
        return produtoMapper.toDomain(produtoEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarProdutosPorCategoria(Categoria categoria) {
        var produtoEntityList = produtoRepository.findByCategoria(categoria);

        return produtoMapper.toDomainList(produtoEntityList);
    }

    @Transactional
    @Override
    public void deletarProduto(UUID id) {
        this.produtoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Produto salvarProduto(Produto Produto) {
        var ProdutoEntity = produtoMapper.toEntity(Produto);

        return produtoMapper.toDomain(produtoRepository.save(ProdutoEntity));
    }
}
