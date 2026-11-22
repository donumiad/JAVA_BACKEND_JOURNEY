package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import java.util.Optional;

public interface ProdutoRepository {

    Produto cadastrar(Produto produto);

    Optional<Produto> buscarPorId(Long id);
}