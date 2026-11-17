package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
    @Transactional
    public boolean atualizar(Produto dadosNovos) {
        Produto produtoGerenciado = entityManager.find(
                Produto.class, dadosNovos.getId()
        );

        if (produtoGerenciado == null) {
            return false;
        }



        produtoGerenciado.setNome(dadosNovos.getNome());
        produtoGerenciado.setPreco(dadosNovos.getPreco());

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return entityManager
                .createQuery("select p from Produto p", Produto.class)
                .getResultList();
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
    @Transactional(readOnly = true)
    public List<Produto> buscarPaginado(
            String nome, int page, int size, String sort, String direction
    ) {
        String atributo = switch (sort) { //VERIFICAR O VALOR DO SORT
            case "id", "nome", "preco", "estoque" -> sort;
            default -> throw new IllegalArgumentException(
                    "Campo de ordenação inválido: " + sort
            );
        };

        //VERIFICA O VALOR DO DIRECTION E VERIFICA SE O NOME É NULL OU ESTA EM BRANCO
        String ordem = "desc".equalsIgnoreCase(direction) ? "desc" : "asc";
        boolean filtrarPorNome = nome != null && !nome.isBlank();


        String consulta = "select p from Produto p"; //BUSCA AS ENTIDADES PRODUTO CHAMDO DE P
        if (filtrarPorNome) {
            consulta += " where p.nome ilike :nome"; //SE O FILTRO PASSAR CONTINUA A CONSTRUÇÃO DO SQL
        }
        consulta += " order by p." + atributo + " " + ordem; //

        TypedQuery<Produto> busca = entityManager.createQuery(consulta, Produto.class);

        if (filtrarPorNome) {
            busca.setParameter("nome", "%" + nome + "%");
        }

        busca.setFirstResult(Math.multiplyExact(page, size));
        busca.setMaxResults(size);

        return busca.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public long contar(String nome) {
        boolean filtrarPorNome = nome != null && !nome.isBlank();

        String consulta = "select count(p) from Produto p";
        if (filtrarPorNome) {
            consulta += " where p.nome ilike :nome";
        }

        TypedQuery<Long> contagem = entityManager.createQuery(consulta, Long.class);

        if (filtrarPorNome) {
            contagem.setParameter("nome", "%" + nome + "%");
        }

        return contagem.getSingleResult();
    }
}