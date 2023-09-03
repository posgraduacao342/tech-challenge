package api.techchallenge.application.config;

import api.techchallenge.application.gateway.*;
import api.techchallenge.application.presenters.mappers.ProdutoMapper;
import api.techchallenge.domain.useCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ClienteService clienteService(ClienteGateway clienteGateway) {
        return new ClienteService(clienteGateway);
    }

    @Bean
    public ItemService itemService(ItemGateway itemGateway) { return new ItemService(itemGateway); }

    @Bean
    public PedidoService pedidoService(PedidoGateway pedidoGateway) {
        return new PedidoService(pedidoGateway);
    }

    @Bean
    public ProdutoService produtoService(ProdutoGateway produtoGateway, ProdutoMapper produtoMapper) {
        return new ProdutoService(produtoGateway, produtoMapper);
    }

    @Bean
    public PagamentoService pagamentoService(PedidoGateway pedidoGateway, PagamentoQrcodeGateway pagamentoQrcodeGateway) {
        var pedidoService = new PedidoService(pedidoGateway);
        return new PagamentoService(pedidoService, pagamentoQrcodeGateway);
    }
}
