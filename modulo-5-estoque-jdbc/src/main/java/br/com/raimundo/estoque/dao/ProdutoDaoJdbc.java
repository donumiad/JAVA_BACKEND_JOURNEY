package br.com.raimundo.estoque.dao;

import br.com.raimundo.estoque.connection.ConnectionFactory;
import br.com.raimundo.estoque.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoJdbc implements ProdutoDao{

@Override
    public List<Produto> listarTodos(){
        List<Produto> produtos = new ArrayList<>();
        String sql = """
                SELECT
                    id,
                    nome,
                    preco,
                    estoque
                FROM produtos
                ORDER BY id
                """;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()){
                Produto produto = mapearProduto(resultSet);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: ", e);
        }

        return produtos;
    }

    private Produto mapearProduto(ResultSet resultSet) throws SQLException {
        Produto produto = new Produto();

        produto.setId(resultSet.getLong("id"));
        produto.setNome(resultSet.getString("nome"));
        produto.setPreco(resultSet.getBigDecimal("preco"));
        produto.setEstoque(resultSet.getInt("estoque"));

        return produto;
    }


}
