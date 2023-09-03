package api.techchallenge.application.controllers;

import api.techchallenge.application.presenters.requests.pagamento.GerarQrcodeRequest;
import api.techchallenge.application.presenters.requests.pagamento.PagarPedidoRequest;
import api.techchallenge.application.presenters.requests.pagamento.ProcessarPagamentoMPRequest;
import api.techchallenge.domain.exception.RecursoNaoEncontratoException;
import api.techchallenge.domain.useCases.PagamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public String pagamento(@RequestBody @Valid PagarPedidoRequest pedidoRequest) throws RecursoNaoEncontratoException {
        return this.pagamentoService.atualizarStatusPagamento(UUID.fromString(pedidoRequest.getPedidoId()), pedidoRequest.getStatusPagamento());
    }

   @PostMapping("/mercado-pago/qrcode")
   public String criarPagamento(@RequestBody @Valid GerarQrcodeRequest pedidoRequest) throws RecursoNaoEncontratoException {
       return pagamentoService.gerarQrcode(UUID.fromString(pedidoRequest.getPedidoId()));
   }

    @PostMapping("/mercado-pago/webhooks")
    public String processarPagamento(@RequestBody ProcessarPagamentoMPRequest pedidoRequest) throws RecursoNaoEncontratoException {
        if(pedidoRequest.getData() != null) {
            pagamentoService.validarPagamento(pedidoRequest.getData().getId());
        }
        return "ok";
    }
}