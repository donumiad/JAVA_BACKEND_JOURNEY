package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);

    List<Produto> buscarPorNome(String nome);

    Produto salvar(Produto produto);

    boolean atualizar(Produto produto);
    boolean removerPorId(Long id);

    boolean atualizarEstoque(Long id, Integer novoEstoque);

    List<Produto> buscarPaginado(
            String nome,
            int page,
            int size,
            String sort,
            String direction
    );

    long contar(String nome);
}
