package io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto;

import io.github.projetoparadesenvolvimento.icompras.pedidos.model.enums.TipoPagamento;

public record DadosPagamentoDTO(String dados, TipoPagamento tipoPagamento) {
}
