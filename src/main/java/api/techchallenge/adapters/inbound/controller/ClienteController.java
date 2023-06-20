package api.techchallenge.adapters.inbound.controller;

import api.techchallenge.adapters.inbound.mapper.ClienteRequestParaClienteMapper;
import api.techchallenge.adapters.inbound.request.CriarClienteRequest;
import api.techchallenge.application.core.domain.Cliente;
import java.util.List;

import api.techchallenge.application.core.service.BuscarClientesService;
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
}
