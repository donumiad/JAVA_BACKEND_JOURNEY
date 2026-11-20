
package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Dimensoes;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("test")
class ProdutoRepositoryIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    void deveCarregarProdutoDaMovimentacao() {
        Produto mouse = new Produto(
                null,
                "Mouse da aula 8",
                new BigDecimal("80.00"),
                10L);
        produtoRepository.salvar(mouse);
        entityManager.flush();

        Movimentacao entrada = Movimentacao.entrada(mouse, 2L);
        entityManager.persist(entrada);
        entityManager.flush();

        Long movimentacaoId = entrada.getId();

        entityManager.clear();

        Movimentacao encontrada = entityManager.find(Movimentacao.class, movimentacaoId);

        assertNotNull(encontrada);
        Produto produtoDaMovimentacao = encontrada.getProduto();
        assertNotNull(produtoDaMovimentacao);
        assertEquals(mouse.getId(), produtoDaMovimentacao.getId());
        assertEquals("Mouse da aula 8", produtoDaMovimentacao.getNome());

        List<Movimentacao> historico = produtoDaMovimentacao.getMovimentacoes();
        assertEquals(1, historico.size());
        assertEquals(movimentacaoId, historico.get(0).getId());
    }

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
                10L
        );

        Dimensoes medidas = new Dimensoes(20, 10, 30);
        produto.definirDimensoes(medidas);

        // 3. Executar o INSERT real
        Produto salvo = produtoRepository.salvar(produto);//entçao aqui o banco ainda não foi atualizado, pois o flush não aocnteceu?

        entityManager.flush();
        entityManager.clear();

        assertNotNull(salvo.getId());

        // 4. Executar o SELECT real
        Optional<Produto> resultado =
                produtoRepository.buscarPorId(salvo.getId());

        assertTrue(resultado.isPresent());

        assertEquals(medidas, resultado.orElseThrow().getDimensoes());

        // 5. Verificar os dados recuperados
        Produto recuperado = resultado.orElseThrow();

        assertEquals(salvo.getId(), recuperado.getId());
        assertEquals("Mouse de Integracao", recuperado.getNome());
        assertEquals(10L, recuperado.getEstoque());

        assertEquals(
                0,
                new BigDecimal("100.00")
                        .compareTo(recuperado.getPreco())
        );
    }
}
