package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.produto.ProdutoRequestParaProdutoMapper;
import api.techchallenge.application.requests.produto.CriarProdutoRequest;
import api.techchallenge.domain.core.domain.Produto;
import api.techchallenge.domain.core.enums.Categoria;
import api.techchallenge.domain.ports.in.ProdutoServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {
    private final ProdutoServicePort produtoServicePort;
    private final ProdutoRequestParaProdutoMapper produtoRequestParaProdutoMapper;

    @PostMapping
    public Produto SalvarProduto (@RequestBody @Valid CriarProdutoRequest request){
        return this.produtoServicePort.salvarProduto(this.produtoRequestParaProdutoMapper.convert(request));
    }

    @GetMapping
    public List<Produto> buscarProdutos(){
        return this.produtoServicePort.buscarProdutos();
    }

    @DeleteMapping(value = "/{produtoId}")
    public void deletarProduto(@PathVariable(value = "produtoId") String produtoId) {
        this.produtoServicePort.deletarProduto(UUID.fromString(produtoId));
    }

    @GetMapping(value = "/{produtoId}")
    public Produto buscarProdutoPorId(@PathVariable(value = "produtoId") String produtoId) throws UserPrincipalNotFoundException {
        return this.produtoServicePort.buscarProdutoPorId(UUID.fromString(produtoId));
    }

    @GetMapping(value = "/porCategoria")
    public List<Produto> buscarProdutosPorCategoria(@RequestParam Categoria categoria) {
        return this.produtoServicePort.buscarProdutosPorCategoria(categoria);
    }
}
