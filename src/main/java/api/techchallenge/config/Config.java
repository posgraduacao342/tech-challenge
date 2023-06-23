package api.techchallenge.config;

import api.techchallenge.adapters.*;
import api.techchallenge.application.core.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ClienteService clienteService(ClienteAdapter clienteAdapter){
        return new ClienteService(clienteAdapter);
    }
}
