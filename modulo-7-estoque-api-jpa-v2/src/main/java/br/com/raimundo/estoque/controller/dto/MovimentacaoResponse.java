package br.com.raimundo.estoque.controller.dto;

import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.model.enums.TipoMovimentacao;

import java.time.Instant;

public record MovimentacaoResponse(
        Long id,
        Long produtoId,
        TipoMovimentacao tipo,
        long quantidade,
        Instant dataMovimentacao
) {
    public static MovimentacaoResponse de(Movimentacao movimentacao) {
        return new MovimentacaoResponse(
                movimentacao.getId(),
                movimentacao.getProduto().getId(),
                movimentacao.getTipo(),
                movimentacao.getQuantidade(),
                movimentacao.getDataMovimentacao()
        );
    }
}
