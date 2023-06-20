package api.techchallenge.adapters.inbound.mapper;

import api.techchallenge.adapters.inbound.request.CriarClienteRequest;
import api.techchallenge.application.core.domain.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClienteRequestParaClienteMapper {
    public Cliente mapper(CriarClienteRequest clienteRequest){
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        return cliente;
    }

}
