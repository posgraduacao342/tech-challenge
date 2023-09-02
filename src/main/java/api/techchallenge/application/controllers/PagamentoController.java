package api.techchallenge.application.controllers;

import api.techchallenge.application.requests.pagamento.PagarPedidoRequest;
import api.techchallenge.domain.core.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.core.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/pagamento")
@AllArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public String pagamento(@RequestBody @Valid PagarPedidoRequest pedidoRequest) throws RecursoNaoEncontratoException {
        return this.pagamentoService.pagarPedido(UUID.fromString(pedidoRequest.getPedidoId()));
    }

    @GetMapping("/{pedidoId}")
    public String buscarStatusPagamento(@PathVariable UUID pedidoId) {
        return this.pagamentoService.buscarStatusPagamentoPorPedidoId(pedidoId);
    }
}