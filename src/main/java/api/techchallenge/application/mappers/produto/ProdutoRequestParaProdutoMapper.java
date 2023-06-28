package api.techchallenge.application.mappers.produto;


import api.techchallenge.application.requests.produto.AtualizarProdutoRequest;
import api.techchallenge.application.requests.produto.CriarProdutoRequest;
import api.techchallenge.domain.core.domain.Produto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Component
public class ProdutoRequestParaProdutoMapper {
    public Produto convert(CriarProdutoRequest request){
        var produto = new Produto();
        BeanUtils.copyProperties(request, produto);
        return produto;
    }

    public void convert(AtualizarProdutoRequest request, Produto target){
        if (request.nome != null) {
            target.setNome(request.nome);
        }
        if (request.preco != null) {
            target.setPreco(request.preco);
        }
        if (request.imagem != null) {
            target.setImagem(request.imagem);
        }
        if (request.descricao != null) {
            target.setDescricao(request.descricao);
        }
        if (request.categoria != null) {
            target.setCategoria(request.categoria);
        }
        target.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
    }
}
