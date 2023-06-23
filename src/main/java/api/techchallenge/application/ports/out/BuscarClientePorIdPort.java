package api.techchallenge.application.ports.out;

import api.techchallenge.application.core.domain.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

public interface BuscarClientePorIdPort {
    Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException;
}
