package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Movimentacao;

import java.util.List;

public interface MovimentacaoRepository {

    Movimentacao salvar(Movimentacao movimentacao);

    List<Movimentacao> listarTodas();

    List<Movimentacao> listarPorProduto(Long produtoId);
}
