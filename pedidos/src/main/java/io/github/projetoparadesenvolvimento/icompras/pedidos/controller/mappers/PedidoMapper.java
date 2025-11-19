package io.github.projetoparadesenvolvimento.icompras.pedidos.controller.mappers;

import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto.ItemPedidoDTO;
import io.github.projetoparadesenvolvimento.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.ItemPedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import io.github.projetoparadesenvolvimento.icompras.pedidos.model.enums.StatusPedido;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    ItemPedidoMapper ITEM_PEDIDO_MAPPER = Mappers.getMapper(ItemPedidoMapper.class);

    @Mapping(source = "itens", target = "itens", qualifiedByName = "mapItens")
    @Mapping(source = "dadosPagamento", target = "dadosPagamento")
    Pedido map(NovoPedidoDTO dto);

    @Named("mapItens")
    default List<ItemPedido> mapItens(List<ItemPedidoDTO> itens){
        return itens.stream().map(ITEM_PEDIDO_MAPPER::map).toList();
    }
    @AfterMapping
    default void afterMapping(@MappingTarget Pedido pedido){
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        pedido.setDataPedido(LocalDateTime.now());

        BigDecimal total = calcularTotal(pedido);

        pedido.setTotal(total);
    }

    private static BigDecimal calcularTotal(Pedido pedido) {
        BigDecimal total = pedido.getItens().stream().map(itemPedido ->
            itemPedido.getValorUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
        return total;
    }
}
