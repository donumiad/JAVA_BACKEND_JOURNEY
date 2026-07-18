package br.com.raimundo.estoque.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/loja_simples";
    private static final String USER = "postgres";
    private static final String PASSWORD = "sua senha";

    //Metodo para abrir conexão com o banco
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
