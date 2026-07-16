package br.com.raimundo.estoque.dao;

import br.com.raimundo.estoque.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProdutoDao {

    List<Produto> listarTodos();

    void salvar(Produto produto);

    Optional<Produto> buscarPorId(Long id);

    List<Produto> buscarPorNome(String trecho);

    void atualizarEstoque(Long id, Integer novaEstoque);

    void atualizar(Produto produto);

    void removerPorId(Long id);

    boolean incrementarEstoque(
            Connection connection,
            Long idProduto,
            Integer quantidade
    ) throws SQLException;
}


