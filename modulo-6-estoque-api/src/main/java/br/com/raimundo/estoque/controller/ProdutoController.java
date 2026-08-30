package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.dto.ProdutoCreateRequest;
import br.com.raimundo.estoque.dto.ProdutoResponse;
import br.com.raimundo.estoque.dto.ProdutoUpdateRequest;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ProdutoResponse buscarPorId(
            @PathVariable Long id
    ) {
        Produto produto =
                produtoService.buscarPorId(id);

        return ProdutoResponse.from(produto);
//        Optional<Produto> produtoEncontrado =
//                Optional.ofNullable(produtoService.buscarPorId(id));
//
//        if (produtoEncontrado.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        ProdutoResponse response =
//                ProdutoResponse.from(produtoEncontrado.get());
//
//        return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoResponse> cadastrar(
            @Valid @RequestBody ProdutoCreateRequest request
    ) {

        Produto produto = new Produto(
                null,
                request.nome(),
                request.preco(),
                request.estoque()
        );

        Produto produtoCriado =
                produtoService.cadastrar(produto);

        ProdutoResponse response =
                ProdutoResponse.from(produtoCriado);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoCriado.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoUpdateRequest request
    ) {

        Optional<Produto> produtoAtualizado =
                produtoService.atualizar(
                        id,
                        request.nome(),
                        request.preco()
                );

        if (produtoAtualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProdutoResponse response =
                ProdutoResponse.from(produtoAtualizado.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id
    ) {

        boolean removido =
                produtoService.remover(id);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}