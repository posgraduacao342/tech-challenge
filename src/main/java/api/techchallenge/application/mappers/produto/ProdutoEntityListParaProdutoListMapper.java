package api.techchallenge.application.mappers.produto;


import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.infrastructure.db.postgres.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoEntityListParaProdutoListMapper {
    private final ProdutoEntityParaProdutoMapper produtoEntityParaProdutoMapper;

    public ProdutoEntityListParaProdutoListMapper(ProdutoEntityParaProdutoMapper produtoEntityParaProdutoMapper) {
        this.produtoEntityParaProdutoMapper = produtoEntityParaProdutoMapper;
    }

    public List<Produto> convert(List<ProdutoEntity> produtoEntityList){
        var produtos = new ArrayList<Produto>();
        for (ProdutoEntity produtoEntity: produtoEntityList) {
            produtos.add(this.produtoEntityParaProdutoMapper.convert(produtoEntity));
        }
        return produtos;
    }
}
