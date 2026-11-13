package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositoryJpa implements ProdutoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Produto salvar(Produto produto) {
        entityManager.persist(produto);
        return produto;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Produto> buscarPorId(Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        return Optional.ofNullable(produto);
    }

    @Transactional
    @Override
    public boolean removerPorId(Long id) {
        Produto produto = entityManager.find(Produto.class, id);

        if (produto == null) {
            return false;
        }

        entityManager.remove(produto);
        return true;
    }

    @Override
    public boolean atualizar(Produto produto) {
        return false;
    }

    @Override
    public List<Produto> listarTodos() {
        return List.of();
    }



    @Override
    public List<Produto> buscarPorNome(String nome) {
        return List.of();
    }



    @Override
    public boolean atualizarEstoque(Long id, Long novoEstoque) {
        return false;
    }

    @Override
    public List<Produto> buscarPaginado(String nome, int page, int size, String sort, String direction) {
        return List.of();
    }

    @Override
    public long contar(String nome) {
        return 0;
    }
}