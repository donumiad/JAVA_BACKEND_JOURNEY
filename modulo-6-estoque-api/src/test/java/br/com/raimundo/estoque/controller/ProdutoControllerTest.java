package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.exception.ProdutoNaoEncontradoException;
import br.com.raimundo.estoque.model.PaginaProduto;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

    @Test
    @DisplayName("GET /api/produtos retorna 200 com lista paginada")
    void deveListarProdutosERetornar200() throws Exception {

        Produto produto = new Produto(
                1L,
                "Mouse",
                new BigDecimal("100.00"),
                10
        );

        PaginaProduto pagina = new PaginaProduto(
                List.of(produto),
                0,
                10,
                1,
                false
        );

        when(
                produtoService.listarPaginado(
                        null,
                        0,
                        10,
                        "nome",
                        "asc"
                )
        ).thenReturn(pagina);

        mockMvc.perform( get("/api/produtos") )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.total").value(1))
                .andExpect(jsonPath("$.itens[0].id").value(1))
                .andExpect(jsonPath("$.itens[0].nome").value("Mouse"))
                .andExpect(jsonPath("$.itens[0].estoque").value(10));

        verify(produtoService)
                .listarPaginado(
                        null,
                        0,
                        10,
                        "nome",
                        "asc"
                );
    }

    @Test
    @DisplayName("GET /api/produtos/{id} retorna 200 quando produto existe")
    void deveBuscarProdutoExistenteERetornar200() throws Exception {

        Produto produto = new Produto(
                1L,
                "Mouse",
                new BigDecimal("100.00"),
                10
        );

        when(
                produtoService.buscarPorId(1L)
        ).thenReturn(produto);

        mockMvc.perform(
                        get("/api/produtos/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Mouse"))
                .andExpect(jsonPath("$.preco").value(100.00))
                .andExpect(jsonPath("$.estoque").value(10));

        verify(produtoService).buscarPorId(1L);
    }

    @Test
    @DisplayName("GET /api/produtos/{id} retorna 404 quando produto não existe")
    void deveRetornar404QuandoProdutoNaoExiste() throws Exception {

        when(
                produtoService.buscarPorId(999L)
        ).thenThrow(
                new ProdutoNaoEncontradoException(999L)
        );

        mockMvc.perform(
                        get("/api/produtos/999")
                )
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith("application/problem+json"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Produto não encontrado"));

        verify(produtoService).buscarPorId(999L);
    }

    @Test
    @DisplayName("POST /api/produtos retorna 201 com produto criado")
    void deveCadastrarProdutoValidoERetornar201() throws Exception {

        Produto produtoCriado = new Produto(
                10L,
                "Teclado",
                new BigDecimal("250.00"),
                5
        );

        when(
                produtoService.cadastrar(any(Produto.class))
        ).thenReturn(produtoCriado);

        String json = """
            {
              "nome": "Teclado",
              "preco": 250.00,
              "estoque": 5
            }
            """;

        mockMvc.perform(
                        post("/api/produtos")
                                .contentType("application/json")
                                .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.nome").value("Teclado"))
                .andExpect(jsonPath("$.preco").value(250.00))
                .andExpect(jsonPath("$.estoque").value(5))
                .andExpect(
                        header().string(
                                "Location",
                                endsWith("/api/produtos/10")
                        )
                );

        verify(produtoService)
                .cadastrar(any(Produto.class));
    }

    @Test
    @DisplayName("POST /api/produtos retorna 400 quando nome está em branco")
    void deveRetornar400AoCadastrarProdutoInvalido() throws Exception {

        String json = """
            {
              "nome": "",
              "preco": -10.00,
              "estoque": -5
            }
            """;

        mockMvc.perform(
                        post("/api/produtos")
                                .contentType("application/json")
                                .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith("application/problem+json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").value("Dados inválidos"));

        verify(produtoService, never())
                .cadastrar(any(Produto.class));
    }
}