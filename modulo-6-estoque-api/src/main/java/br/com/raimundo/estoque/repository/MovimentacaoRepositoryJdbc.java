package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Movimentacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovimentacaoRepositoryJdbc
        implements MovimentacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final MovimentacaoRowMapper rowMapper =
            new MovimentacaoRowMapper();

    public MovimentacaoRepositoryJdbc(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movimentacao salvar(
            Movimentacao movimentacao
    ) {

        String sql = """
                INSERT INTO movimentacoes_estoque
                    (produto_id, tipo_movimentacao, quantidade)
                VALUES (?, ?, ?)
                RETURNING
                    id,
                    produto_id,
                    tipo_movimentacao,
                    quantidade,
                    data_movimentacao
                """;

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                movimentacao.getProdutoId(),
                movimentacao.getTipo(),
                movimentacao.getQuantidade()
        );
    }

    @Override
    public List<Movimentacao> listarTodas() {

        String sql = """
                SELECT
                    id,
                    produto_id,
                    tipo_movimentacao,
                    quantidade,
                    data_movimentacao
                FROM movimentacoes_estoque
                ORDER BY data_movimentacao DESC
                """;

        return jdbcTemplate.query(sql, rowMapper);
    }
}