package io.github.projetoparadesenvolvimento.icompras.pedidos.client;

import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ServicoBancarioClient {

    public String solicitarPagamento(Pedido pedido){
        log.info("Solicitando pagamento para o pedido {}.", pedido.getCodigo());
        return UUID.randomUUID().toString();
    }
}
