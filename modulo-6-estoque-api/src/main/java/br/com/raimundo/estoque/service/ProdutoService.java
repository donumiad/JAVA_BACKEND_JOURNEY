package br.com.raimundo.estoque.service;

import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.listarTodos();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.buscarPorNome(nome);
    }

    public Produto cadastrar(Produto produto) {
        return produtoRepository.salvar(produto);
    }

    public Optional<Produto> atualizar(
            Long id,
            String nome,
            BigDecimal preco
    ) {

        Optional<Produto> produtoExistente =
                produtoRepository.buscarPorId(id);

        if (produtoExistente.isEmpty()) {
            return Optional.empty();
        }

        Produto atual = produtoExistente.get();

        Produto produtoAtualizado = new Produto(
                atual.getId(),
                nome,
                preco,
                atual.getEstoque()
        );

        produtoRepository.atualizar(produtoAtualizado);

        return Optional.of(produtoAtualizado);
    }

    public boolean remover(Long id) {
        return produtoRepository.removerPorId(id);
    }
}