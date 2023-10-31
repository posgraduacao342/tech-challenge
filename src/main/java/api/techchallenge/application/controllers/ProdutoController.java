package api.techchallenge.application.controllers;

import api.techchallenge.application.presenters.mappers.ProdutoMapper;
import api.techchallenge.application.presenters.requests.produto.AtualizarProdutoRequest;
import api.techchallenge.application.presenters.requests.produto.CriarProdutoRequest;
import api.techchallenge.domain.entities.Produto;
import api.techchallenge.domain.enums.Categoria;
import api.techchallenge.domain.ports.in.ProdutoUseCasesPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {
    private final ProdutoUseCasesPort produtoUseCasesPort;
    private final ProdutoMapper produtoMapper;

    @PostMapping
    public Produto salvarProduto (@RequestBody @Valid CriarProdutoRequest request){
        return this.produtoUseCasesPort.criarNovoProduto(produtoMapper.toDomain(request));
    }

    @GetMapping
    public List<Produto> buscarProdutos(){
        return this.produtoUseCasesPort.buscarProdutos();
    }

    @PatchMapping(value = "/{produtoId}")
    public Produto atualizarProduto(@PathVariable UUID produtoId, @RequestBody AtualizarProdutoRequest request){
        return this.produtoUseCasesPort.atualizarProduto(produtoId, request);
    }

    @DeleteMapping(value = "/{produtoId}")
    public void deletarProduto(@PathVariable(value = "produtoId") String produtoId) {
        this.produtoUseCasesPort.deletarProduto(UUID.fromString(produtoId));
    }

    @GetMapping(value = "/{produtoId}")
    public Produto buscarProdutoPorId(@PathVariable(value = "produtoId") String produtoId) {
        return this.produtoUseCasesPort.buscarProdutoPorId(UUID.fromString(produtoId));
    }

    @GetMapping(value = "/porCategoria")
    public List<Produto> buscarProdutosPorCategoria(@RequestParam Categoria categoria) {
        return this.produtoUseCasesPort.buscarProdutosPorCategoria(categoria);
    }
}
