package io.github.projetoparadesenvolvimento.icompras.pedidos.controller.mappers;

import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto.ItemPedidoDTO;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedido map(ItemPedidoDTO dto);
}
