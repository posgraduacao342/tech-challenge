package api.techchallenge.application.presenters.mappers;

import api.techchallenge.application.presenters.requests.cliente.AtualizarClienteRequest;
import api.techchallenge.application.presenters.responses.cliente.CriarClienteResponse;
import api.techchallenge.domain.entities.Cliente;
import api.techchallenge.domain.valueObjects.CPF;
import api.techchallenge.domain.valueObjects.Email;
import api.techchallenge.infrastructure.db.entity.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper {

    public ClienteEntity toEntity(Cliente cliente) {
        var clienteEntity = new ClienteEntity();
        clienteEntity.setId(cliente.getId());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setEmail(String.valueOf(cliente.getEmail()));
        clienteEntity.setCpf(String.valueOf(cliente.getCpf()));

        clienteEntity.setDataAtualizacao(cliente.getDataAtualizacao());
        clienteEntity.setDataCriacao(cliente.getDataCriacao());
        return clienteEntity;
    }

    public Cliente toDomain(ClienteEntity clienteEntity) {
        var cliente = new Cliente();
        var email = new Email(clienteEntity.getEmail());
        var cpf = new CPF(clienteEntity.getCpf());
        cliente.setId(clienteEntity.getId());
        cliente.setNome(clienteEntity.getNome());
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setDataAtualizacao(clienteEntity.getDataAtualizacao());
        cliente.setDataCriacao(clienteEntity.getDataCriacao());
        return cliente;
    }

    public CriarClienteResponse toResponse(Cliente cliente) {
        var response = new CriarClienteResponse();
        response.setDataCriacao(cliente.getDataCriacao());
        response.setDataDelecao(cliente.getDataDelecao());
        response.setDataAtualizacao(cliente.getDataAtualizacao());
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setEmail(String.valueOf(cliente.getEmail()));
        response.setCpf(String.valueOf(cliente.getCpf()));
        response.setPedidos(cliente.getPedidos());
        return response;
    }

    public List<CriarClienteResponse> toResponse(List<Cliente> clienteList) {
        var response = new ArrayList<CriarClienteResponse>();
        clienteList.forEach(item -> {
            response.add(toResponse(item));
        });
        return response;
    }

    public Cliente toDomain(AtualizarClienteRequest request) {
        var cliente = new Cliente();
        var email = new Email(request.getEmail());
        var cpf = new CPF(request.getCpf());
        cliente.setNome(request.getNome());
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        return cliente;
    }


}