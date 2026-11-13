package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Movimentacao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository {

    Movimentacao salvar(Movimentacao movimentacao);

    List<Movimentacao> listarTodas();
}