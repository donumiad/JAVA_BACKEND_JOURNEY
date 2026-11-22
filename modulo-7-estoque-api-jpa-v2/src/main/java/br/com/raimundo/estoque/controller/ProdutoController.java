package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.controller.dto.ProdutoRequest;
import br.com.raimundo.estoque.controller.dto.ProdutoResponse;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(
            @Valid @RequestBody ProdutoRequest pedido) {

        Produto salvo = produtoService.cadastrar(
                pedido.nome(),
                pedido.preco(),
                pedido.paraDimensoes()
        );

        URI endereco = URI.create("/produtos/" + salvo.getId());
        ProdutoResponse resposta = ProdutoResponse.de(salvo);

        return ResponseEntity.created(endereco).body(resposta);
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable("id") Long id) {
        Produto produto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado."
                ));

        return ProdutoResponse.de(produto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail tratarDadosInvalidos(IllegalArgumentException erro) {
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, erro.getMessage()
        );
    }
}