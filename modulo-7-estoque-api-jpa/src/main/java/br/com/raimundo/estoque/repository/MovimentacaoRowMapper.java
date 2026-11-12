package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Movimentacao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimentacaoRowMapper
        implements RowMapper<Movimentacao> {

    @Override
    public Movimentacao mapRow(
            ResultSet rs,
            int rowNum
    ) throws SQLException {

        return new Movimentacao(
                rs.getLong("id"),
                rs.getLong("produto_id"),
                rs.getString("tipo_movimentacao"),
                rs.getInt("quantidade"),
                rs.getObject(
                        "data_movimentacao",
                        java.time.LocalDateTime.class
                )
        );
    }
}