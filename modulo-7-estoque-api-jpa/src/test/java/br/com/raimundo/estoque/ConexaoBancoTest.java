package br.com.raimundo.estoque;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("test")
class ConexaoBancoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void deveUtilizarBancoDeTestes() {

        String bancoAtual = jdbcTemplate.queryForObject(
                "SELECT current_database()",
                String.class
        );

        assertEquals("estoque_api_test", bancoAtual);
    }
}