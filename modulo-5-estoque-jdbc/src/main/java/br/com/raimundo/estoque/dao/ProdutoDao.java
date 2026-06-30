package br.com.raimundo.estoque.dao;

import br.com.raimundo.estoque.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoDao {

    List<Produto> listarTodos();

    void salvar(Produto produto);

    Optional<Produto> buscarPorId(Long id);

    List<Produto> buscarPorNome(String trecho);

}
