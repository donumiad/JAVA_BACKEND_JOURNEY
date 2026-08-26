package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.dto.MovimentacaoRequest;
import br.com.raimundo.estoque.dto.MovimentacaoResponse;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(
            MovimentacaoService movimentacaoService
    ) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping("/movimentacoes")
    public List<MovimentacaoResponse> listar() {

        return movimentacaoService.listarTodas()
                .stream()
                .map(MovimentacaoResponse::from)
                .toList();
    }

    @PostMapping("/produtos/{id}/entradas")
    public ResponseEntity<MovimentacaoResponse> registrarEntrada(
            @PathVariable Long id,
            @Valid @RequestBody MovimentacaoRequest request
    ) {

        Optional<Movimentacao> movimentacao =
                movimentacaoService.registrarEntrada(
                        id,
                        request.quantidade()
                );

        if (movimentacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        MovimentacaoResponse.from(
                                movimentacao.get()
                        )
                );
    }

    @PostMapping("/produtos/{id}/saidas")
    public ResponseEntity<MovimentacaoResponse> registrarSaida(
            @PathVariable Long id,
            @Valid @RequestBody MovimentacaoRequest request
    ) {

        Optional<Movimentacao> movimentacao =
                movimentacaoService.registrarSaida(
                        id,
                        request.quantidade()
                );

        if (movimentacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        MovimentacaoResponse.from(
                                movimentacao.get()
                        )
                );
    }
}