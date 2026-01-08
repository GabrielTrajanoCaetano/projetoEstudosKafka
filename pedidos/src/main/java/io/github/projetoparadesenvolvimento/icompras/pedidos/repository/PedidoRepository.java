package io.github.projetoparadesenvolvimento.icompras.pedidos.repository;

import io.github.projetoparadesenvolvimento.icompras.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByCodigoCliente(Long codigoCliente);

    Optional<Pedido> findByCodigoAndChavePagamento(Long codigo, String chavePagamento);
}
