package api.techchallenge.config;

import api.techchallenge.adapters.SalvarClienteAdapter;
import api.techchallenge.application.core.service.SalvarClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cliente {
    @Bean
    public SalvarClienteService salvarClienteService(SalvarClienteAdapter salvarClienteAdapter){
        return new SalvarClienteService(salvarClienteAdapter);
    }
}
