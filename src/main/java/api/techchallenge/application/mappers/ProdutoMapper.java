package api.techchallenge.application.mappers;

import api.techchallenge.application.requests.produto.AtualizarProdutoRequest;
import api.techchallenge.application.requests.produto.CriarProdutoRequest;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.infrastructure.db.entity.ProdutoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoMapper {
    public Produto toDomain(ProdutoEntity produtoEntity){
        var produto = new Produto();
        BeanUtils.copyProperties(produtoEntity, produto);
        return produto;
    }
    public Produto toDomainWithId(ProdutoEntity produtoEntity){
        var produto = new Produto();
        BeanUtils.copyProperties(produtoEntity, produto);
        produto.setId(produtoEntity.getId());
        return produto;
    }
    public List<Produto> toDomainList(List<ProdutoEntity> produtoEntityList){
        var produtos = new ArrayList<Produto>();
        for (ProdutoEntity produtoEntity: produtoEntityList) {
            produtos.add(toDomain(produtoEntity));
        }
        return produtos;
    }

    public Produto toDomain(CriarProdutoRequest request){
        var produto = new Produto();
        BeanUtils.copyProperties(request, produto);
        return produto;
    }

    public Produto toDomain(AtualizarProdutoRequest request, Produto produto){
        if (request.nome != null) {
            produto.setNome(request.nome);
        }
        if (request.preco != null) {
            produto.setPreco(request.preco);
        }
        if (request.imagem != null) {
            produto.setImagem(request.imagem);
        }
        if (request.descricao != null) {
            produto.setDescricao(request.descricao);
        }
        if (request.categoria != null) {
            produto.setCategoria(request.categoria);
        }
        produto.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

        return produto;
    }

    public ProdutoEntity toEntity(Produto produto){
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produto, produtoEntity);
        produtoEntity.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        produtoEntity.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
        return produtoEntity;
    }
}
