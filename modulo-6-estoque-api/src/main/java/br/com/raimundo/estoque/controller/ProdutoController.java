package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.dto.ProdutoResponse;
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

    @GetMapping()
    public List<ProdutoResponse> listar(
            @RequestParam(required = false) String nome
    ) {
        List<Produto> produtos;

        if (nome == null || nome.isBlank()) {
            produtos = produtoService.listarTodos();
        } else {
            produtos = produtoService.buscarPorNome(nome);
        }

        return produtos.stream()
                .map(ProdutoResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(
            @PathVariable Long id
    ) {
        Optional<Produto> produtoEncontrado =
                produtoService.buscarPorId(id);

        if (produtoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProdutoResponse response =
                ProdutoResponse.from(produtoEncontrado.get());

        return ResponseEntity.ok(response);
    }
}