package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositoryJdbc implements ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProdutoRowMapper rowMapper = new ProdutoRowMapper();

    private static final String COLUNAS =
            "id, nome, preco, estoque";

    public ProdutoRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Produto> listarTodos() {
        String sql = "SELECT " + COLUNAS + " FROM produtos ORDER BY nome ";

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        String sql = """
                SELECT id, nome, preco, estoque
                FROM produtos
                WHERE id = ?
                """;

        try {
            Produto produto = jdbcTemplate.queryForObject(
                    sql,
                    rowMapper,
                    id
            );

            return Optional.of(produto);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        String sql = """
                SELECT id, nome, preco, estoque
                FROM produtos
                WHERE nome ILIKE ?
                ORDER BY nome
                """;

        return jdbcTemplate.query(
                sql,
                rowMapper,
                "%" + nome + "%"
        );
    }
}
