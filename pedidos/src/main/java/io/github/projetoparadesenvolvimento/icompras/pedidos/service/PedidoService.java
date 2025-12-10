package io.github.projetoparadesenvolvimento.icompras.pedidos.service;

import io.github.projetoparadesenvolvimento.icompras.pedidos.client.ServicoBancarioClient;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.projetoparadesenvolvimento.icompras.pedidos.repository.PedidoRepository;
import io.github.projetoparadesenvolvimento.icompras.pedidos.validator.PedidoValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final ServicoBancarioClient servicoBancarioClient;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        pedidoValidator.validar(pedido);
        realizarPersistencia(pedido);
        enviarSolicitacaoPagamento(pedido);


        return pedido;
    }

    private void enviarSolicitacaoPagamento(Pedido pedido) {
        var chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);
    }

    private void realizarPersistencia(Pedido pedido) {
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
    }
}
