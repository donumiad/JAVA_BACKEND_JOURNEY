package br.com.raimundo.estoque.service;

import br.com.raimundo.estoque.controller.dto.MovimentacaoResponse;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.repository.MovimentacaoRepository;
import br.com.raimundo.estoque.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovimentacaoService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(ProdutoRepository produtoRepository,
                               MovimentacaoRepository movimentacaoRepository) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional
    public MovimentacaoResponse registrarEntrada(Long produtoId, long quantidade) {
        Produto produto = buscarParaMovimentar(produtoId);
        produto.adicionarEstoque(quantidade);
        Movimentacao entrada = Movimentacao.entrada(produto, quantidade);
        return MovimentacaoResponse.de(movimentacaoRepository.salvar(entrada));
    }

    @Transactional
    public MovimentacaoResponse registrarSaida(Long produtoId, long quantidade) {
        Produto produto = buscarParaMovimentar(produtoId);

        if (!produto.retirarEstoque(quantidade)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Saldo insuficiente para a saída."
            );
        }

        Movimentacao saida = Movimentacao.saida(produto, quantidade);
        return MovimentacaoResponse.de(movimentacaoRepository.salvar(saida));
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoResponse> listar(Long produtoId) {
        List<Movimentacao> movimentacoes;

        if (produtoId == null) {
            movimentacoes = movimentacaoRepository.listarTodas();
        } else {
            produtoRepository.buscarPorId(produtoId)
                    .orElseThrow(() -> produtoNaoEncontrado(produtoId));
            movimentacoes = movimentacaoRepository.listarPorProduto(produtoId);
        }

        return movimentacoes.stream()
                .map(MovimentacaoResponse::de)
                .toList();
    }

    private Produto buscarParaMovimentar(Long produtoId) {
        return produtoRepository.buscarParaMovimentar(produtoId)
                .orElseThrow(() -> produtoNaoEncontrado(produtoId));
    }

    private ResponseStatusException produtoNaoEncontrado(Long produtoId) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado: " + produtoId
        );
    }
}
