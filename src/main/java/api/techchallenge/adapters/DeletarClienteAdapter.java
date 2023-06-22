package api.techchallenge.adapters;

import api.techchallenge.adapters.outbound.repositories.ClienteRepository;
import api.techchallenge.application.ports.out.DeletarClientePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeletarClienteAdapter implements DeletarClientePort {
    private final ClienteRepository clienteRepository;

    @Override
    public void deletar(UUID id) {
        this.clienteRepository.deleteById(id);
    }
}
