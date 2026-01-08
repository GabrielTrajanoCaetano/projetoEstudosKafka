package io.github.projetoparadesenvolvimento.icompras.pedidos.service;

import io.github.projetoparadesenvolvimento.icompras.pedidos.client.ServicoBancarioClient;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.DadosPagamento;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.enums.StatusPedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.enums.TipoPagamento;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.exception.ItemNaoEncontradoException;
import io.github.projetoparadesenvolvimento.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.projetoparadesenvolvimento.icompras.pedidos.repository.PedidoRepository;
import io.github.projetoparadesenvolvimento.icompras.pedidos.validator.PedidoValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public void atualizarStatusPagamento(
            Long codigoPedido, String chavePagamento, boolean sucesso, String observacoes) {
        var pedidoEncontrado = pedidoRepository.findByCodigoAndChavePagamento(codigoPedido, chavePagamento);

        if(pedidoEncontrado.isEmpty()){
            var msg = String.format("Pedido não encontrado para o codigo %d e chave %s",
                    codigoPedido, chavePagamento);
            log.error(msg);
            return;
        }

        Pedido pedido = pedidoEncontrado.get();

        if(sucesso){
            pedido.setStatusPedido(StatusPedido.PAGO);
        }else {
            pedido.setStatusPedido(StatusPedido.ERRO_PAGAMENTO);
            pedido.setObservacoes(observacoes);
        }

        pedidoRepository.save(pedido);

    }

    @Transactional
    public void adicionarNovoPagamento(Long codigoPedido, String dadosCartao, TipoPagamento tipo){
        var pedidoEncontrado =  pedidoRepository.findById(codigoPedido);

        if(pedidoEncontrado.isEmpty()){
            throw new ItemNaoEncontradoException("Pedido não encontrado para o codigo informado");
        }

        var pedido = pedidoEncontrado.get();

        DadosPagamento dadosPagamento =  new DadosPagamento();
        dadosPagamento.setTipoPagamento(tipo);
        dadosPagamento.setDados(dadosCartao);

        pedido.setDadosPagamento(dadosPagamento);
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        pedido.setObservacoes("Novo pagamento realizado, aguardando o processamento.");

        pedidoRepository.save(pedido);

        String novaChavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(novaChavePagamento);
    }
}
