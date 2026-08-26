package br.com.raimundo.estoque.dto;

import br.com.raimundo.estoque.model.Movimentacao;

import java.time.LocalDateTime;

public record MovimentacaoResponse(
        Long id,
        Long produtoId,
        String tipo,
        Integer quantidade,
        LocalDateTime dataMovimentacao
) {

    public static MovimentacaoResponse from(
            Movimentacao movimentacao
    ) {
        return new MovimentacaoResponse(
                movimentacao.getId(),
                movimentacao.getProdutoId(),
                movimentacao.getTipo(),
                movimentacao.getQuantidade(),
                movimentacao.getDataMovimentacao()
        );
    }
}