package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProdutoRepositoryJpa implements ProdutoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Produto cadastrar(Produto produto) {
        entityManager.persist(produto);
        return produto;
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        return Optional.ofNullable(produto);
    }
}
