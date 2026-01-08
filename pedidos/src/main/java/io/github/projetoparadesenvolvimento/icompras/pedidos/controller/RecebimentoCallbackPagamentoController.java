package io.github.projetoparadesenvolvimento.icompras.pedidos.controller;

import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto.RecebimentoCallbackPagamentoDTO;
import io.github.projetoparadesenvolvimento.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/callback-pagamentos")
@RequiredArgsConstructor
public class RecebimentoCallbackPagamentoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> atualizarStatusDePagamento(
            @RequestBody RecebimentoCallbackPagamentoDTO body,
            @RequestHeader(required = true, name = "apikey") String apiKey
            ){

        pedidoService.atualizarStatusPagamento(body.codigo(),
                body.chavePagamento(),
                body.status(),
                body.observacoes());
        return ResponseEntity.ok().build();
    }
}
