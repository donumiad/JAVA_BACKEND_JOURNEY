package br.com.raimundo.estoque.service;

import br.com.raimundo.estoque.exception.EstoqueInsuficienteException;
import br.com.raimundo.estoque.exception.ProdutoNaoEncontradoException;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.repository.MovimentacaoRepository;
import br.com.raimundo.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(
            ProdutoRepository produtoRepository,
            MovimentacaoRepository movimentacaoRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.listarTodas();
    }

    @Transactional
    public Optional<Movimentacao> registrarEntrada(
            Long produtoId,
            Integer quantidade
    ) {

//        Optional<Produto> produtoEncontrado =
//                produtoRepository.buscarPorId(produtoId);
//
//        if (produtoEncontrado.isEmpty()) {
//            return Optional.empty();
//        }

        Produto produto = produtoRepository.buscarPorId(produtoId)
                .orElseThrow(
                        () -> new ProdutoNaoEncontradoException(produtoId)
                );

        //Produto produto = produtoEncontrado.get();

        int novoEstoque =
                produto.getEstoque() + quantidade;

        produtoRepository.atualizarEstoque(
                produtoId,
                novoEstoque
        );

        Movimentacao movimentacao =
                new Movimentacao(
                        produtoId,
                        "ENTRADA",
                        quantidade
                );

        return Optional.of(
                movimentacaoRepository.salvar(movimentacao)
        );
    }


//    public Optional<Movimentacao> registrarSaida(
//            Long produtoId,
//            Integer quantidade
//    ) {
//
//        Optional<Produto> produtoEncontrado =
//                produtoRepository.buscarPorId(produtoId);
//
//        if (produtoEncontrado.isEmpty()) {
//            return Optional.empty();
//        }
//
//        Produto produto = produtoEncontrado.get();
//
//        if (quantidade > produto.getEstoque()) {
//            throw new EstoqueInsuficienteException(
//                    produto.getEstoque(),
//                    quantidade
//            );
//        }
    @Transactional
    public Optional<Movimentacao> registrarSaida(
            Long produtoId,
            Integer quantidade
    ) {

        Produto produto = produtoRepository.buscarPorId(produtoId)
                .orElseThrow(
                        () -> new ProdutoNaoEncontradoException(produtoId)
                );

        if (quantidade > produto.getEstoque()) {
            throw new EstoqueInsuficienteException(
                    produto.getEstoque(),
                    quantidade
            );
        }

        int novoEstoque =
                produto.getEstoque() - quantidade;

        produtoRepository.atualizarEstoque(
                produtoId,
                novoEstoque
        );

        Movimentacao movimentacao =
                new Movimentacao(
                        produtoId,
                        "SAIDA",
                        quantidade
                );

        return Optional.of(
                movimentacaoRepository.salvar(movimentacao)
        );
    }
}
