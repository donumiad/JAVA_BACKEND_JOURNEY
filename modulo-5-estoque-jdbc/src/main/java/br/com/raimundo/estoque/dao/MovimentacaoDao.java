package br.com.raimundo.estoque.dao;

import br.com.raimundo.estoque.model.Movimentacao;

import java.sql.Connection;
import java.sql.SQLException;

public interface MovimentacaoDao {
    void registrar(Movimentacao movimentacao, Connection conn) throws SQLException;
}
