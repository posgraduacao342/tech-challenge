package api.techchallenge.application.mappers.produto;


import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.infrastructure.db.postgres.entity.ProdutoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProdutoEntityParaProdutoMapper {
    public Produto convert(ProdutoEntity produtoEntity){
        var produto = new Produto();
        BeanUtils.copyProperties(produtoEntity, produto);
        return produto;
    }
}
