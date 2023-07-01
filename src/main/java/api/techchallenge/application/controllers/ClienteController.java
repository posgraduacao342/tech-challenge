package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.cliente.ClienteRequestParaClienteMapper;
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
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    private final ClienteRequestParaClienteMapper clienteRequestParaClienteMapper;

    @GetMapping
    public List<Cliente> buscarClientes() {
        return this.clienteServicePort.buscarClientes();
    }

    @GetMapping(value = "/{clienteId}")
    public Cliente buscarClientePorId(@PathVariable(value = "clienteId") String clienteId) throws UserPrincipalNotFoundException {
        return this.clienteServicePort.buscarClientePorId(UUID.fromString(clienteId));
    }

    @PostMapping
    public Cliente salvarCliente(@RequestBody @Valid CriarClienteRequest clienteRequest) {
        var cliente = clienteRequestParaClienteMapper.mapper(clienteRequest);
        return this.clienteServicePort.criarNovoCliente(cliente);
    }

    @DeleteMapping(value = "/{clienteId}")
    public void deletar(@PathVariable(value = "clienteId") String clienteId) {
        this.clienteServicePort.deletarCliente(UUID.fromString(clienteId));
    }

    @PatchMapping(value = "/{clienteId}")
    public Cliente atualizarCliente(
            @PathVariable(value = "clienteId") String clienteId,
            @RequestBody @Valid AtualizarClienteRequest atualizarClienteRequest
    ) throws UserPrincipalNotFoundException {
        var cliente = new Cliente();
        BeanUtils.copyProperties(atualizarClienteRequest, cliente);
        return this.clienteServicePort.atualizarCliente(Optional.of(cliente), UUID.fromString(clienteId));
    }

}
