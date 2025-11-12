package io.github.projetoparadesenvolvimento.icompras.clientes.controller;

import io.github.projetoparadesenvolvimento.icompras.clientes.model.Cliente;
import io.github.projetoparadesenvolvimento.icompras.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> salvar (@RequestBody @Valid Cliente cliente){
        Cliente clienteSalvo = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> obterDados(@PathVariable("codigo") Long codigo){
        return clienteService
                .buscarClientePorId(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
