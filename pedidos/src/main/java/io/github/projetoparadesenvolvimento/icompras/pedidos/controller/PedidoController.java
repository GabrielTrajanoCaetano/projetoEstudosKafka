package io.github.projetoparadesenvolvimento.icompras.pedidos.controller;

import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.mappers.PedidoMapper;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO novoPedidoDTO){
        Pedido pedido = pedidoMapper.map(novoPedidoDTO);
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return  ResponseEntity.status(HttpStatus.CREATED).body(novoPedido.getCodigo());

    }
}
