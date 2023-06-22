package api.techchallenge.application.ports.out;

import java.util.UUID;

public interface DeletarClientePort {
    Void deletar(UUID id);
}
