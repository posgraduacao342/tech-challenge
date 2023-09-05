package api.techchallenge.application.config;

import api.techchallenge.application.gateway.*;
import api.techchallenge.application.presenters.mappers.ProdutoMapper;
import api.techchallenge.domain.useCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ClienteUseCases clienteUseCasesConfig(ClienteGateway clienteGateway) {
        return new ClienteUseCases(clienteGateway);
    }

    @Bean
    public ItemUseCases itemUseCasesConfig(ItemGateway itemGateway) {
        return new ItemUseCases(itemGateway);
    }

    @Bean
    public PedidoUseCases pedidoUseCasesConfig(PedidoGateway pedidoGateway, ProdutoGateway produtoGateway) {
        return new PedidoUseCases(pedidoGateway, produtoGateway);
    }

    @Bean
    public ProdutoUseCases produtoUseCasesConfig(ProdutoGateway produtoGateway, ProdutoMapper produtoMapper) {
        return new ProdutoUseCases(produtoGateway, produtoMapper);
    }

    @Bean
    public PagamentoUseCases pagamentoUseCasesConfig(PedidoGateway pedidoGateway, PagamentoQrcodeGateway pagamentoQrcodeGateway, ProdutoGateway produtoGateway) {
        var pedidoService = new PedidoUseCases(pedidoGateway, produtoGateway);
        return new PagamentoUseCases(pedidoService, pagamentoQrcodeGateway);
    }
}
