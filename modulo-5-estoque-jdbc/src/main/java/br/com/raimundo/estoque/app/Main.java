package br.com.raimundo.estoque.app;

import br.com.raimundo.estoque.connection.ConnectionFactory;
import br.com.raimundo.estoque.dao.ProdutoDaoJdbc;
import br.com.raimundo.estoque.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ProdutoDaoJdbc teste = new ProdutoDaoJdbc();
        for (Produto produto: teste.listarTodos()) {
            System.out.println(produto);
        }



    }
}

//TESTE DE CONEXÃO COM O BANCO DE DADOS
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            System.out.println("Conexão aberta com sucesso!");
//            System.out.println(connection);
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar com o banco: " + e.getMessage());
//        }
