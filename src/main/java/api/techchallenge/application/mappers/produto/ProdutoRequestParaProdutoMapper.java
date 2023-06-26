package api.techchallenge.application.mappers.produto;


import api.techchallenge.application.requests.produto.CriarProdutoRequest;
import api.techchallenge.domain.core.domain.Produto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRequestParaProdutoMapper {
    public Produto convert(CriarProdutoRequest request){
        var produto = new Produto();
        BeanUtils.copyProperties(request, produto);
        return produto;
    }
}
