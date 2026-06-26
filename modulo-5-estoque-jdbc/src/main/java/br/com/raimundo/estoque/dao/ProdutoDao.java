package br.com.raimundo.estoque.dao;

import br.com.raimundo.estoque.model.Produto;

import java.util.List;

public interface ProdutoDao {

    List<Produto> listarTodos();

}
