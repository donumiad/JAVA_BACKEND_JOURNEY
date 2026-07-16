package br.com.raimundo.estoque.dao;

import br.com.raimundo.estoque.model.Movimentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoDaoJdbc implements MovimentacaoDao{

    @Override
    public void registrar(Movimentacao movimentacao, Connection conn) throws SQLException {
        String sql = """
                INSERT INTO movimentacoes_estoque 
                    (produto_id, tipo, quantidade) 
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1,    movimentacao.getProdutoId());
            stmt.setString(2,  movimentacao.getTipo());
            stmt.setInt(3,     movimentacao.getQuantidade());

            stmt.executeUpdate();
        }
    }
}
