package api.techchallenge.application.config;

import api.techchallenge.domain.core.service.ProdutoService;
import api.techchallenge.infrastructure.db.postgres.adapters.ClienteAdapter;
import api.techchallenge.domain.core.service.ClienteService;
import api.techchallenge.infrastructure.db.postgres.adapters.ProdutoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ClienteService clienteService(ClienteAdapter clienteAdapter){
        return new ClienteService(clienteAdapter);
    }

    @Bean
    public ProdutoService produtoService(ProdutoAdapter produtoAdapter){
        return new ProdutoService(produtoAdapter);
    }
}
