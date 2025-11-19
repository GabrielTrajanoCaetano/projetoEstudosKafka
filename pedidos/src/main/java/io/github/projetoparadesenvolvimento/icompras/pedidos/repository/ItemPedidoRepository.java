package io.github.projetoparadesenvolvimento.icompras.pedidos.repository;

import io.github.projetoparadesenvolvimento.icompras.pedidos.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
