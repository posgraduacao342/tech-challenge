package api.techchallenge.application.config;

import api.techchallenge.domain.core.service.PedidoService;
import api.techchallenge.application.adapters.ClienteAdapter;
import api.techchallenge.domain.core.service.ClienteService;
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
    public PedidoService pedidoService(PedidoAdapter pedidoAdapter) {
        return new PedidoService(pedidoAdapter);
    }
}
