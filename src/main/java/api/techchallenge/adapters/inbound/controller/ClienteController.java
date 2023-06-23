package api.techchallenge.adapters.inbound.controller;

import api.techchallenge.adapters.inbound.mapper.ClienteRequestParaClienteMapper;
import api.techchallenge.adapters.inbound.request.CriarClienteRequest;
import api.techchallenge.application.core.domain.Cliente;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

import api.techchallenge.application.core.service.BuscarClientePorIdService;
import api.techchallenge.application.core.service.BuscarClientesService;
import api.techchallenge.application.core.service.DeletarClienteService;
import api.techchallenge.application.ports.in.SalvarClienteServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final SalvarClienteServicePort salvarClienteServicePort;

    private final BuscarClientesService buscarClientesService;

    private final DeletarClienteService deletarClienteService;

    private final BuscarClientePorIdService buscarClientePorIdService;

    private final ClienteRequestParaClienteMapper clienteRequestParaClienteMapper;


    @PostMapping
    public Cliente salvarCliente(@RequestBody @Valid CriarClienteRequest clienteRequest){
        var cliente = clienteRequestParaClienteMapper.mapper(clienteRequest);
        return salvarClienteServicePort.salvarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> buscarClientes(){
        return buscarClientesService.buscarClientes();
    }

    @DeleteMapping(value = "/{clienteId}")
    public void deletar(@PathVariable(value = "clienteId") String clienteId) {
        deletarClienteService.deletarCliente(UUID.fromString(clienteId));
    }

    @GetMapping(value = "/{clienteId}")
    public Cliente buscarClientePorId(@PathVariable(value = "clienteId") String clienteId) throws UserPrincipalNotFoundException {
        return buscarClientePorIdService.buscarClientePorId(UUID.fromString(clienteId));
    }
}
