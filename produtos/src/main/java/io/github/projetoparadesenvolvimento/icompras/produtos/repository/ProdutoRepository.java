package io.github.projetoparadesenvolvimento.icompras.produtos.repository;

import io.github.projetoparadesenvolvimento.icompras.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
