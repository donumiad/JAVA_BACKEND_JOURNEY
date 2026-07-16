package br.com.raimundo.estoque.dao;

import br.com.raimundo.estoque.connection.ConnectionFactory;
import br.com.raimundo.estoque.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* Este metodo implementa a comunicação entre o java e o banco de dados, no qual o comandos de
* leitura, atualização e alteração são realizadas via API JDBC.
* essa comunicação esta ligada a penas a tabela produtos.*/

public class ProdutoDaoJdbc implements ProdutoDao{

    private static final String COLUNAS_SELECT =
            "id, nome, preco, estoque";

@Override
    public List<Produto> listarTodos(){
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT " +COLUNAS_SELECT+ " FROM produtos ORDER BY id";

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

    @Override
    public Optional<Produto> buscarPorId(Long id) {

        String sql = "SELECT " + COLUNAS_SELECT + " FROM produtos WHERE id = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    Produto produto = mapearProduto(resultSet);
                    return Optional.of(produto);
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar id do produto: ", e);
        }

    }

    @Override
    public List<Produto> buscarPorNome(String trecho) {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT " + COLUNAS_SELECT + " FROM produtos WHERE nome ILIKE ? ORDER BY nome";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setString(1, "%" + trecho + "%");

            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Produto produto = mapearProduto(resultSet);
                    produtos.add(produto);
                }
            }
            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto com ("+ trecho + "): ",e);
        }
    }

    @Override
    public void salvar(Produto produto) {
        String sql = """
            INSERT INTO produtos (nome, preco, estoque)
            VALUES (?, ?, ?)
            """;

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, produto.getNome());
            statement.setBigDecimal(2, produto.getPreco());
            statement.setInt(3, produto.getEstoque());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    @Override
    public void atualizarEstoque(Long id, Integer novoEstoque) {
        String sql = """
                UPDATE produtos
                SET estoque = estoque + ?
                WHERE id = ?
                """;

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setInt(1, novoEstoque);
            statement.setLong(2, id);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0){
                throw new RuntimeException("Produto não encontrado para o id: " + id);
            }else {
                System.out.println("Estoque atualizado com sucesso no id: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao localizar estoque do produto",e);
        }
    }

    @Override
    public void atualizar(Produto produto) {

        String sql = """
                UPDATE produtos
                SET
                    nome = ?,
                    preco = ?,
                    estoque = ?
                WHERE id = ?
                """;
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){

            statement.setString(1, produto.getNome());
            statement.setBigDecimal(2, produto.getPreco());
            statement.setInt(3, produto.getEstoque());
            statement.setLong(4, produto.getId());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0){
                throw new RuntimeException("Produto não encontrado para o id: " + produto.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto",e);
        }
    }

    @Override
    public void removerPorId(Long id) {

        String sql = """
                DELETE FROM produtos
                WHERE id = ?
                """;
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0){
                throw new RuntimeException("Nenhum produto removido. Id não encontrado: " + id);
            }else {
                System.out.println("O produto com ID: "+ id +" foi removido com sucesso");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover produto", e);
        }
    }

    private Produto mapearProduto(ResultSet resultSet) throws SQLException {
        Produto produto = new Produto();

        produto.setId(resultSet.getLong("id"));
        produto.setNome(resultSet.getString("nome"));
        produto.setPreco(resultSet.getBigDecimal("preco"));
        produto.setEstoque(resultSet.getInt("estoque"));

        return produto;
    }

    @Override
    public boolean incrementarEstoque(Connection connection, Long idProduto, Integer quantidade) throws SQLException {
        String sql = """
            UPDATE produtos
            SET estoque = estoque + ?
            WHERE id = ?
            """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, quantidade);
            statement.setLong(2, idProduto);

            int linhasAfetadas = statement.executeUpdate();

            return linhasAfetadas == 1;
        }
    }
}
