package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.persistence.entity.ProdutoEntity;
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

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        if (produto==null){return Optional.empty();}
        return Optional.of(produto);
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        return List.of();
    }

    @Transactional
    public boolean atualizar(Produto produto) {
        ProdutoEntity entidade = entityManager.find(
                ProdutoEntity.class, produto.getId()
        );

        if (entidade == null) {
            return false;
        }

        entidade.setNome(produto.getNome());
        entidade.setPreco(produto.getPreco());

        return true;
    }

    @Transactional
    public boolean removerPorId(Long id) {
        ProdutoEntity entidade = entityManager.find(ProdutoEntity.class, id);

        if (entidade == null) {
            return false;
        }

        entityManager.remove(entidade);
        return true;
    }

    @Override
    public boolean atualizarEstoque(Long id, Integer novoEstoque) {
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

    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return entityManager.createQuery(
                        "select p from Produto p order by p.id",
                        Produto.class
                )
                .getResultList()
                .stream()
                .toList();
    }

}