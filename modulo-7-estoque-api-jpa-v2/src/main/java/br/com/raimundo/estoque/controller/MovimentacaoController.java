package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.controller.dto.MovimentacaoRequest;
import br.com.raimundo.estoque.controller.dto.MovimentacaoResponse;
import br.com.raimundo.estoque.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping("/entradas")
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentacaoResponse registrarEntrada(
            @Valid @RequestBody MovimentacaoRequest pedido) {
        return movimentacaoService.registrarEntrada(
                pedido.produtoId(), pedido.quantidade()
        );
    }

    @PostMapping("/saidas")
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentacaoResponse registrarSaida(
            @Valid @RequestBody MovimentacaoRequest pedido) {
        return movimentacaoService.registrarSaida(
                pedido.produtoId(), pedido.quantidade()
        );
    }

    @GetMapping
    public List<MovimentacaoResponse> listar(
            @RequestParam(name = "produtoId", required = false) Long produtoId) {
        return movimentacaoService.listar(produtoId);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ProblemDetail tratarLimiteDeEstoque(ArithmeticException erro) {
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, "A entrada ultrapassa o limite de estoque."
        );
    }
}