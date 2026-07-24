package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);

    List<Produto> buscarPorNome(String nome);
}
