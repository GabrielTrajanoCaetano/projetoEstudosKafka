package io.github.projetoparadesenvolvimento.icompras.clientes.repository;

import io.github.projetoparadesenvolvimento.icompras.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
