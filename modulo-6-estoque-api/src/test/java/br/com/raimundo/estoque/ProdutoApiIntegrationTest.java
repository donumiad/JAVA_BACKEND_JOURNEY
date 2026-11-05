
package br.com.raimundo.estoque;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProdutoApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    void deveCadastrarEConsultarProdutoPelaApi() throws Exception {

        // 1. Verifica o banco antes de alterar dados
        String bancoAtual = jdbcTemplate.queryForObject(
                "SELECT current_database()",
                String.class
        );

        assertEquals("estoque_api_test", bancoAtual);

        // 2. Prepara o corpo JSON da requisição
        String json = """
                {
                    "nome": "Mouse Integracao API",
                    "preco": 100.00,
                    "estoque": 10
                }
                """;

        // 3. Executa POST para cadastrar o produto
        MvcResult resultado = mockMvc.perform(
                        post("/api/produtos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Mouse Integracao API"))
                .andExpect(jsonPath("$.estoque").value(10))
                .andReturn();

        // 4. Recupera o endereço do produto cadastrado
        String location = resultado
                .getResponse()
                .getHeader("Location");

        assertNotNull(location);

        String caminhoProduto = URI.create(location).getPath();

        // 5. Executa GET para consultar o produto
        mockMvc.perform(get(caminhoProduto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome")
                        .value("Mouse Integracao API"))
                .andExpect(jsonPath("$.estoque").value(10));
    }
}
