
package br.com.raimundo.estoque;

import br.com.raimundo.estoque.persistence.entity.ProdutoEntity;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("test")
class InfraestruturaJpaTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void deveInicializarInfraestruturaJpaEJdbc() {

        assertNotNull(entityManagerFactory);
        assertNotNull(jdbcTemplate);
    }
    @Test
    void deveReconhecerProdutoComoEntidadeJpa() {

        var entidade = entityManagerFactory
                .getMetamodel()
                .entity(ProdutoEntity.class);

        assertNotNull(entidade);
    }
}
