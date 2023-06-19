package api.techchallenge.adapters.inbound.controller;

import api.techchallenge.adapters.inbound.mapper.ClienteRequestParaClienteMapper;
import api.techchallenge.adapters.inbound.request.ClienteRequest;
import api.techchallenge.application.core.domain.Cliente;
import api.techchallenge.application.ports.in.SalvarClienteServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final SalvarClienteServicePort salvarClienteServicePort;

    private final ClienteRequestParaClienteMapper clienteRequestParaClienteMapper;

    @PostMapping
    public Cliente salvarCliente(@RequestBody @Valid  ClienteRequest clienteRequest){
        var cliente = clienteRequestParaClienteMapper.mapper(clienteRequest);
        return salvarClienteServicePort.salvarCliente(cliente);
    }
}
