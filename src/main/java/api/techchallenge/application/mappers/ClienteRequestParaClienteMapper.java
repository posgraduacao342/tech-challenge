package api.techchallenge.application.mappers;

import api.techchallenge.application.requets.CriarClienteRequest;
import api.techchallenge.domain.core.domain.Cliente;
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
