package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Movimentacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovimentacaoRepositoryJpa implements MovimentacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Movimentacao salvar(Movimentacao movimentacao) {
        entityManager.persist(movimentacao);
        return movimentacao;
    }

    @Override
    public List<Movimentacao> listarTodas() {
        return entityManager.createQuery("""
            select m from Movimentacao m
            order by m.dataMovimentacao desc, m.id desc
            """, Movimentacao.class)
                .getResultList();
    }

    @Override
    public List<Movimentacao> listarPorProduto(Long produtoId) {
        return entityManager.createQuery("""
            select m from Movimentacao m
            where m.produto.id = :produtoId
            order by m.dataMovimentacao desc, m.id desc
            """, Movimentacao.class)
                .setParameter("produtoId", produtoId)
                .getResultList();
    }
}