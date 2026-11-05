
package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("test")
class ProdutoRepositoryIntegrationTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    void deveSalvarEBuscarProdutoNoBanco() {

        // 1. Confirmar o banco antes de alterar dados
        String bancoAtual = jdbcTemplate.queryForObject(
                "SELECT current_database()",
                String.class
        );

        assertEquals("estoque_api_test", bancoAtual);

        // 2. Preparar os dados
        Produto produto = new Produto(
                null,
                "Mouse de Integracao",
                new BigDecimal("100.00"),
                10
        );

        // 3. Executar o INSERT real
        Produto salvo = produtoRepository.salvar(produto);

        assertNotNull(salvo.getId());

        // 4. Executar o SELECT real
        Optional<Produto> resultado =
                produtoRepository.buscarPorId(salvo.getId());

        assertTrue(resultado.isPresent());

        // 5. Verificar os dados recuperados
        Produto recuperado = resultado.orElseThrow();

        assertEquals(salvo.getId(), recuperado.getId());
        assertEquals("Mouse de Integracao", recuperado.getNome());
        assertEquals(10, recuperado.getEstoque());

        assertEquals(
                0,
                new BigDecimal("100.00")
                        .compareTo(recuperado.getPreco())
        );
    }
}
