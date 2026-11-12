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
public class ProdutoRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        ProdutoEntity entidade = entityManager.find(ProdutoEntity.class, id);

        return Optional.ofNullable(entidade).map(this::paraProduto);
    }

    @Transactional
    public Produto salvar(Produto produto) {
        ProdutoEntity entidade = new ProdutoEntity(
                produto.getNome(),
                produto.getPreco(),
                produto.getEstoque().longValue()
        );

        entityManager.persist(entidade);

        return paraProduto(entidade);
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

    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return entityManager.createQuery(
                        "select p from ProdutoEntity p order by p.id",
                        ProdutoEntity.class
                )
                .getResultList()
                .stream()
                .map(this::paraProduto)
                .toList();
    }

    private Produto paraProduto(ProdutoEntity entidade) {
        return new Produto(
                entidade.getId(),
                entidade.getNome(),
                entidade.getPreco(),
                Math.toIntExact(entidade.getEstoque())
        );
    }
}