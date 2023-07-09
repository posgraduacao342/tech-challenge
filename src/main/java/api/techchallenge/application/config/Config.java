package api.techchallenge.application.config;

import api.techchallenge.application.adapters.ItemAdapter;
import api.techchallenge.application.adapters.ProdutoAdapter;
import api.techchallenge.application.mappers.ProdutoMapper;
import api.techchallenge.domain.core.service.*;
import api.techchallenge.application.adapters.ClienteAdapter;
import api.techchallenge.application.adapters.PedidoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ClienteService clienteService(ClienteAdapter clienteAdapter) {
        return new ClienteService(clienteAdapter);
    }

    @Bean
    public ItemService itemService(ItemAdapter itemAdapter) { return new ItemService(itemAdapter); }

    @Bean
    public PedidoService pedidoService(PedidoAdapter pedidoAdapter) {
        return new PedidoService(pedidoAdapter);
    }

    @Bean
    public ProdutoService produtoService(ProdutoAdapter produtoAdapter, ProdutoMapper produtoMapper) {
        return new ProdutoService(produtoAdapter, produtoMapper);
    }

    @Bean
    public PagamentoService pagamentoService(PedidoAdapter pedidoAdapter) {
        var pedidoService = new PedidoService(pedidoAdapter);
        return new PagamentoService(pedidoService);
    }
}
