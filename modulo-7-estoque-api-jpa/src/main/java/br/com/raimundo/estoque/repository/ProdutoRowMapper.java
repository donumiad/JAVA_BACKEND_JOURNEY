package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoRowMapper implements RowMapper<Produto> {

    @Override
    public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Produto(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getBigDecimal("preco"),
                rs.getInt("estoque")
        );
    }
}