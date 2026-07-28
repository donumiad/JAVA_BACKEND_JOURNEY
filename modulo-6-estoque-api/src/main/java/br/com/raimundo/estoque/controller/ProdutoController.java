package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listar(
            @RequestParam(required = false) String nome
    ) {
        if (nome == null || nome.isBlank()) {
            return produtoService.listarTodos();
        }

        return produtoService.buscarPorNome(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @PathVariable Long id
    ) {
        Optional<Produto> produtoEncontrado =
                produtoService.buscarPorId(id);

        if (produtoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoEncontrado.get());
    }
}