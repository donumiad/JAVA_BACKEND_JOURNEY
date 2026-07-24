package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class ProdutoRepositoryMemoria implements ProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();

    public ProdutoRepositoryMemoria() {
        produtos.add(new Produto(
                1L,
                "Teclado Mecânico",
                new BigDecimal("250.00"),
                15
        ));

        produtos.add(new Produto(
                2L,
                "Mouse Gamer",
                new BigDecimal("120.00"),
                30
        ));

        produtos.add(new Produto(
                3L,
                "Monitor 24 Polegadas",
                new BigDecimal("850.00"),
                8
        ));
    }

    @Override
    public List<Produto> listarTodos() {
        return List.copyOf(produtos);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        String termoBuscado = nome.toLowerCase(Locale.ROOT);

        return produtos.stream()
                .filter(produto ->
                        produto.getNome()
                                .toLowerCase(Locale.ROOT)
                                .contains(termoBuscado)
                )
                .toList();
    }
}
