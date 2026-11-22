package br.com.raimundo.estoque.service;

import br.com.raimundo.estoque.model.Dimensoes;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto cadastrar(String nome, BigDecimal preco, Dimensoes dimensoes) {
        Produto produto = new Produto(nome, preco, dimensoes);
        return produtoRepository.cadastrar(produto);
    }

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }
}