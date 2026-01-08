package io.github.projetoparadesenvolvimento.icompras.pedidos.controller;

import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto.AdicaoNovoPagamentoDto;
import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.mappers.PedidoMapper;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.ErroResposta;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.exception.ItemNaoEncontradoException;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.exception.ValidationException;
import io.github.projetoparadesenvolvimento.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO novoPedidoDTO){
        try {

            Pedido pedido = pedidoMapper.map(novoPedidoDTO);
            Pedido novoPedido = pedidoService.criarPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido.getCodigo());
        } catch (ValidationException e){
            ErroResposta erro = new ErroResposta("Erro validação", e.getField(), e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    @PostMapping("pagamentos")
    public ResponseEntity<Object> adicionarNovoPagamento(@RequestBody AdicaoNovoPagamentoDto dto){
        try {
            pedidoService.adicionarNovoPagamento(dto.codigoPedido(), dto.dados(), dto.tipoPagamento());
            return ResponseEntity.noContent().build();
        }catch (ItemNaoEncontradoException ex){
            var erro = new ErroResposta(
                    "Item não encontrado", "codigoPedido", ex.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }
}
