package io.github.projetoparadesenvolvimento.icompras.pedidos.service;

import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.projetoparadesenvolvimento.icompras.pedidos.repository.PedidoRepository;
import io.github.projetoparadesenvolvimento.icompras.pedidos.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;

    public Pedido criarPedido(Pedido pedido){
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
        return pedido;
    }

}
