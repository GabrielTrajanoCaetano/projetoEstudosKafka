package io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto;

import io.github.projetoparadesenvolvimento.icompras.pedidos.model.enums.TipoPagamento;

public record AdicaoNovoPagamentoDto(Long codigoPedido, String dados, TipoPagamento tipoPagamento) {
}
