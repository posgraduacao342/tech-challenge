package api.techchallenge.application.config;

import api.techchallenge.application.mappers.produto.ProdutoRequestParaProdutoMapper;
import api.techchallenge.domain.core.service.PagamentoService;
import api.techchallenge.domain.core.service.PedidoService;
import api.techchallenge.domain.core.service.ProdutoService;
import api.techchallenge.infrastructure.db.postgres.adapters.ClienteAdapter;
import api.techchallenge.domain.core.service.ClienteService;
import api.techchallenge.infrastructure.db.postgres.adapters.PedidoAdapter;
import api.techchallenge.infrastructure.db.postgres.adapters.ProdutoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ClienteService clienteService(ClienteAdapter clienteAdapter) {
        return new ClienteService(clienteAdapter);
    }

    @Bean
    public ProdutoService produtoService(ProdutoAdapter produtoAdapter, ProdutoRequestParaProdutoMapper produtoRequestParaProdutoMapper) {
        return new ProdutoService(produtoAdapter, produtoRequestParaProdutoMapper);
    }

    @Bean
    public PedidoService pedidoService(PedidoAdapter pedidoAdapter) {
        return new PedidoService(pedidoAdapter);
    }

    @Bean
    public PagamentoService pagamentoService(PedidoAdapter pedidoAdapter) {
        var pedidoService = new PedidoService(pedidoAdapter);
        return new PagamentoService(pedidoService);
    }
}
