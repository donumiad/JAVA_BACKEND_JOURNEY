package br.com.raimundo.estoque.controller;

import br.com.raimundo.estoque.exception.EstoqueInsuficienteException;
import br.com.raimundo.estoque.service.MovimentacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
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
                movimentacaoService.registrarSaida(
                        1L,
                        100
                )
        ).thenThrow(
                new EstoqueInsuficienteException(
                        10,
                        100
                )
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
                .andExpect(
                        content()
                                .contentTypeCompatibleWith(
                                        "application/problem+json"
                                )
                )
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.title").value("Estoque insuficiente"));
    }
}