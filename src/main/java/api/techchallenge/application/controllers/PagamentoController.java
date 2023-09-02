package api.techchallenge.application.controllers;

import api.techchallenge.application.presenters.requests.pagamento.PagarPedidoRequest;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.useCases.PagamentoUseCases;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
public class PagamentoController {

    private final PagamentoUseCases pagamentoUseCases;

    @PostMapping
    public String pagamento(@RequestBody @Valid PagarPedidoRequest pedidoRequest) throws RecursoNaoEncontratoException {
        return this.pagamentoUseCases.pagarPedido(UUID.fromString(pedidoRequest.getPedidoId()));
    }

    @GetMapping("/{pedidoId}")
    public String buscarStatusPagamento(@PathVariable UUID pedidoId) {
        return this.pagamentoUseCases.buscarStatusPagamentoPorPedidoId(pedidoId);
    }
}