package io.github.projetoparadesenvolvimento.icompras.produtos.controller;

import io.github.projetoparadesenvolvimento.icompras.produtos.model.Produto;
import io.github.projetoparadesenvolvimento.icompras.produtos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
@Tag(name = "Produto", description = "Classe utilizada para criação de recursos de produto")
public class ProdutoController {

    private final ProdutoService service;

    @Operation(summary = "Salvar um produto", description = "Recurso criado para salvar um novo produto",
              responses = {
                    @ApiResponse(responseCode = "201", description = "recurso criado com sucesso",
                    content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = Produto.class)))
              })

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        service.salvar(produto);
        log.info("Produto salvo com sucesso: {}", produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);

    }

    @Operation(summary = "Buscar produto", description = "Recurso criado para buscar um produto através do id/codigo",
    responses = {
            @ApiResponse(responseCode = "200", description = "Recurso retornou com sucesso",
            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = Produto.class)))
    })

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarProdutoPeloId(@PathVariable("codigo") Long codigo){
        log.info("Produto encontrado, codigo solicitado: {} ",codigo);
        return service
                .obterPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


}
