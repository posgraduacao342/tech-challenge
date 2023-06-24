package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.produto.ProdutoRequestParaProdutoMapper;
import api.techchallenge.application.requests.produto.CriarProdutoRequest;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.ports.in.ProdutoServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {
    private final ProdutoServicePort ProdutoServicePort;
    private final ProdutoRequestParaProdutoMapper ProdutoRequestParaProdutoMapper;

    @PostMapping
    public Produto SalvarProduto (@RequestBody @Valid CriarProdutoRequest request){
        return this.ProdutoServicePort.salvarProduto(this.ProdutoRequestParaProdutoMapper.mapper(request));
    }
}
