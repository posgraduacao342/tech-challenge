package api.techchallenge.application.controllers;

import api.techchallenge.application.mappers.ClienteRequestParaClienteMapper;
import api.techchallenge.application.requests.CriarClienteRequest;
import api.techchallenge.domain.core.domain.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

import api.techchallenge.domain.ports.in.ClienteServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    private final ClienteRequestParaClienteMapper clienteRequestParaClienteMapper;


    @PostMapping
    public Cliente salvarCliente(@RequestBody @Valid CriarClienteRequest clienteRequest){
        var cliente = clienteRequestParaClienteMapper.mapper(clienteRequest);
        return this.clienteServicePort.salvarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> buscarClientes(){
        return this.clienteServicePort.buscarClientes();
    }

    @DeleteMapping(value = "/{clienteId}")
    public void deletar(@PathVariable(value = "clienteId") String clienteId) {
        this.clienteServicePort.deletarCliente(UUID.fromString(clienteId));
    }

    @GetMapping(value = "/{clienteId}")
    public Cliente buscarClientePorId(@PathVariable(value = "clienteId") String clienteId) throws UserPrincipalNotFoundException {
        return this.clienteServicePort.buscarClientePorId(UUID.fromString(clienteId));
    }
}
