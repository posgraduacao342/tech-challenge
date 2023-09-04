package api.techchallenge.application.config;

import api.techchallenge.application.gateway.*;
import api.techchallenge.application.presenters.mappers.ProdutoMapper;
import api.techchallenge.domain.useCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ClienteUseCases clienteUseCases(ClienteGateway clienteGateway) {
        return new ClienteUseCases(clienteGateway);
    }

    @Bean
    public ItemUseCases itemUseCases(ItemGateway itemGateway) { return new ItemUseCases(itemGateway); }

    @Bean
    public PedidoUseCases pedidoUseCases(PedidoGateway pedidoGateway) {
        return new PedidoUseCases(pedidoGateway);
    }

    @Bean
    public ProdutoUseCases produtoUseCases(ProdutoGateway produtoGateway, ProdutoMapper produtoMapper) {
        return new ProdutoUseCases(produtoGateway, produtoMapper);
    }

    @Bean
    public PagamentoUseCases pagamentoUseCases(PedidoGateway pedidoGateway, PagamentoQrcodeGateway pagamentoQrcodeGateway) {
        var pedidoService = new PedidoUseCases(pedidoGateway);
        return new PagamentoUseCases(pedidoService, pagamentoQrcodeGateway);
    }
}
