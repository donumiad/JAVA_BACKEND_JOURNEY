package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.dto.ProdutoCreateRequest;
import br.com.raimundo.estoque.dto.ProdutoPageResponse;
import br.com.raimundo.estoque.dto.ProdutoResponse;
import br.com.raimundo.estoque.dto.ProdutoUpdateRequest;
import br.com.raimundo.estoque.model.PaginaProduto;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@Tag(
        name = "Produtos",
        description = "Operações de cadastro, consulta, atualização e remoção de produtos"
)
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


//    public List<ProdutoResponse> listar(
//             @RequestParam(required = false) String nome
//    ) {
//        List<Produto> produtos;
//
//        if (nome == null || nome.isBlank()) {
//            produtos = produtoService.listarTodos();
//        } else {
//            produtos = produtoService.buscarPorNome(nome);
//        }
//
//        return produtos.stream()
//                .map(ProdutoResponse::from)
//                .toList();
//    }

    @Operation(
            summary = "Listar produtos",
            description = "Lista produtos com filtro opcional por nome, paginação e ordenação."
    )
    @GetMapping
    public ProdutoPageResponse listar(
            @Parameter(
                    description = "Trecho opcional do nome do produto",
                    example = "mouse"
            )
            @RequestParam(required = false)
            String nome,

            @Parameter(
                    description = "Número da página, iniciando em zero",
                    example = "0"
            )
            @RequestParam(defaultValue = "0")
            int page,

            @Parameter(
                    description = "Quantidade de produtos por página",
                    example = "10"
            )
            @RequestParam(defaultValue = "10")
            int size,

            @Parameter(
                    description = "Campo usado para ordenação: id, nome, preco ou estoque",
                    example = "preco"
            )
            @RequestParam(defaultValue = "nome")
            String sort,

            @Parameter(
                    description = "Direção da ordenação: asc ou desc",
                    example = "desc"
            )
            @RequestParam(defaultValue = "asc")
            String direction
    ) {

        PaginaProduto pagina =
                produtoService.listarPaginado(
                        nome,
                        page,
                        size,
                        sort,
                        direction
                );

        return ProdutoPageResponse.from(pagina);
    }

    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna os dados de um produto cadastrado a partir do seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Produto encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado"
            )
    })
    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(

            @Parameter(
                    description = "Identificador do produto",
                    example = "1"
            )
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

    @Operation(
            summary = "Cadastrar produto",
            description = "Cria um novo produto no estoque."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Produto criado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de entrada inválidos"
            )
    })
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