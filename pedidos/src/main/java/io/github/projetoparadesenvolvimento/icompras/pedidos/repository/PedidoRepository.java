package io.github.projetoparadesenvolvimento.icompras.pedidos.repository;

import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
