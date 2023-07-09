package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.GenericMapper;
import api.techchallenge.application.requests.cliente.AtualizarClienteRequest;
import api.techchallenge.application.requests.cliente.CriarClienteRequest;
import api.techchallenge.domain.core.domain.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.techchallenge.domain.ports.in.ClienteServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicePort clienteServicePort;
    private final GenericMapper clienteMapper;

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
    public Cliente buscarClientePorCpf(@PathVariable(value = "cpf") String cpf) throws UserPrincipalNotFoundException {
        return this.clienteServicePort.buscarClientePorCpf(cpf);
    }

    @PostMapping
    public Cliente salvarCliente(@RequestBody @Valid CriarClienteRequest clienteRequest) {
        var cliente = clienteMapper.toTransform(clienteRequest, Cliente.class);
        return this.clienteServicePort.criarNovoCliente(cliente);
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
        var cliente = clienteMapper.toTransform(atualizarClienteRequest, Cliente.class);
        return this.clienteServicePort.atualizarCliente(Optional.of(cliente), UUID.fromString(clienteId));
    }

}
