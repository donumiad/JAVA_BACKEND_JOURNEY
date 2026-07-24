package br.com.raimundo.estoque.service;

import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

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
}