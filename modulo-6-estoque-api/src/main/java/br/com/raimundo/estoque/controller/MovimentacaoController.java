package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.dto.MovimentacaoRequest;
import br.com.raimundo.estoque.dto.MovimentacaoResponse;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "Movimentações de estoque",
        description = "Operações de entrada, saída e consulta do histórico de estoque"
)
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

    @Operation(
            summary = "Registrar entrada de estoque",
            description = "Acrescenta unidades ao estoque do produto e registra a movimentação."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Entrada registrada"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Quantidade inválida"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado"
            )
    })
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

    @Operation(
            summary = "Registrar saída de estoque",
            description = "Retira unidades do estoque e registra a movimentação."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Saída registrada"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Quantidade inválida"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Estoque insuficiente"
            )
    })
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