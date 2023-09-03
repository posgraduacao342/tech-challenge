package api.techchallenge.application.controllers;

import api.techchallenge.application.presenters.mappers.ClienteMapper;
import api.techchallenge.application.presenters.mappers.GenericMapper;
import api.techchallenge.application.presenters.requests.cliente.AtualizarClienteRequest;
import api.techchallenge.application.presenters.requests.cliente.CriarClienteRequest;
import api.techchallenge.application.presenters.responses.cliente.CriarClienteResponse;
import api.techchallenge.domain.entities.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.techchallenge.domain.exception.RecursoJaExisteException;
import api.techchallenge.domain.ports.in.ClienteServicePort;
import api.techchallenge.domain.valueObjects.CPF;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicePort clienteServicePort;
    private final GenericMapper genericMapper;
    private final ClienteMapper clienteMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Cliente> buscarClientes() {
        return this.clienteServicePort.buscarClientes();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{clienteId}")
    public Cliente buscarClientePorId(@PathVariable(value = "clienteId") String clienteId) throws UserPrincipalNotFoundException {
        return this.clienteServicePort.buscarClientePorId(UUID.fromString(clienteId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/porCpf/{cpf}")
    public Cliente buscarClientePorCpf(@PathVariable(value = "cpf") CPF cpf) throws UserPrincipalNotFoundException {
        return this.clienteServicePort.buscarClientePorCpf(String.valueOf(cpf));
    }

    @PostMapping
    public CriarClienteResponse salvarCliente(@RequestBody @Valid CriarClienteRequest clienteRequest) throws RecursoJaExisteException {
        return clienteMapper.clienteToCriarClienteResponse(
                this.clienteServicePort.criarNovoCliente(clienteRequest.getNome(), clienteRequest.getEmail(), clienteRequest.getCpf())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{clienteId}")
    public void deletar(@PathVariable(value = "clienteId") String clienteId) {
        this.clienteServicePort.deletarCliente(UUID.fromString(clienteId));
    }

    @PatchMapping(value = "/{clienteId}")
    public Cliente atualizarCliente(
            @PathVariable(value = "clienteId") String clienteId,
            @RequestBody @Valid AtualizarClienteRequest atualizarClienteRequest
    ) throws UserPrincipalNotFoundException {
        var cliente = genericMapper.toTransform(atualizarClienteRequest, Cliente.class);
        return this.clienteServicePort.atualizarCliente(Optional.of(cliente), UUID.fromString(clienteId));
    }

}