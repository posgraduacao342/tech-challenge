package api.techchallenge.application.ports.in;

import api.techchallenge.application.core.domain.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

public interface BuscarClientePorIdServicePort {
    Cliente buscarClientePorId(UUID id) throws UserPrincipalNotFoundException;
}
