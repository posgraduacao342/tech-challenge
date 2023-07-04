package api.techchallenge.application.mappers.produto;

import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.infrastructure.db.entity.ProdutoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ProdutoParaProdutoEntityMapper {
    public ProdutoEntity convert(Produto produto){
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produto, produtoEntity);
        produtoEntity.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        produtoEntity.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
        return produtoEntity;
    }
}
