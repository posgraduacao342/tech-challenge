package api.techchallenge.config;

import api.techchallenge.adapters.SalvarClienteAdapter;
import api.techchallenge.application.core.service.BuscarClientesService;
import api.techchallenge.application.core.service.DeletarClienteService;
import api.techchallenge.application.core.service.SalvarClienteService;
import api.techchallenge.application.ports.out.BuscarClientesPort;
import api.techchallenge.application.ports.out.DeletarClientePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cliente {
    @Bean
    public SalvarClienteService salvarClienteService(SalvarClienteAdapter salvarClienteAdapter){
        return new SalvarClienteService(salvarClienteAdapter);
    }

    @Bean
    public BuscarClientesService buscarClientesService(BuscarClientesPort buscarClientesPort){
        return new BuscarClientesService(buscarClientesPort);
    }

    @Bean
    public DeletarClienteService deletarClienteService(DeletarClientePort deletarClientePort){
        return new DeletarClienteService(deletarClientePort);
    }
}
