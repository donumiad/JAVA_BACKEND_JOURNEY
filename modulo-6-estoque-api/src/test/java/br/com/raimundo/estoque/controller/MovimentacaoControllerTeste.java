package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.exception.EstoqueInsuficienteException;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.service.MovimentacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovimentacaoController.class)
class MovimentacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovimentacaoService movimentacaoService;

    @Test
    @DisplayName("POST /api/produtos retorna 400 quando preço é negativo")
    void deveRetornar409QuandoEstoqueForInsuficiente()
            throws Exception {

        when(
                movimentacaoService.registrarSaida(1L, 100)
        ).thenThrow(
                new EstoqueInsuficienteException(10, 100)
        );

        String json = """
            {
              "quantidade": 100
            }
            """;

        mockMvc.perform(
                        post("/api/produtos/1/saidas")
                                .contentType("application/json")
                                .content(json)
                )
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith("application/problem+json"))
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.title").value("Estoque insuficiente"));
    }

    @Test
    @DisplayName("GET /api/movimentacoes retorna List<movimentacaoResponse>")
    void deveListarMovimentacoesERetornar200()
            throws Exception {

        Movimentacao movimentacaoUm = new Movimentacao(
                1L,10L, "SAIDA", 15, LocalDateTime.now());
        Movimentacao movimentacaoDois = new Movimentacao(
                2L,20L, "ENTRADA", 20, LocalDateTime.now());
        Movimentacao movimentacaoTres = new Movimentacao(
                3L,30L, "SAIDA", 30, LocalDateTime.now());

        List<Movimentacao> movimentacaoList =
                List.of(movimentacaoUm, movimentacaoDois, movimentacaoTres);

        when(
                movimentacaoService.listarTodas()
        ).thenReturn(movimentacaoList);

        mockMvc.perform( get("/api/movimentacoes") )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))

                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].produtoId").value(10))
                .andExpect(jsonPath("$[0].tipo").value("SAIDA"))
                .andExpect(jsonPath("$[0].quantidade").value(15))

                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].produtoId").value(20))
                .andExpect(jsonPath("$[1].tipo").value("ENTRADA"))
                .andExpect(jsonPath("$[1].quantidade").value(20))

                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].produtoId").value(30))
                .andExpect(jsonPath("$[2].tipo").value("SAIDA"))
                .andExpect(jsonPath("$[2].quantidade").value(30));

        verify(movimentacaoService).listarTodas();

    }
}