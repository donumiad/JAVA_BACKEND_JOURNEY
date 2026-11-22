package br.com.raimundo.estoque.controller.dto;

import br.com.raimundo.estoque.model.Dimensoes;
import br.com.raimundo.estoque.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        long estoque,
        DimensoesResponse dimensoes
) {
    public record DimensoesResponse(
            int larguraCm,
            int alturaCm,
            int profundidadeCm
    ) {
    }

    public static ProdutoResponse de(Produto produto) {
        Dimensoes medidas = produto.getDimensoes();
        DimensoesResponse dimensoesResposta = null;

        if (medidas != null) {
            dimensoesResposta = new DimensoesResponse(
                    medidas.getLarguraCm(),
                    medidas.getAlturaCm(),
                    medidas.getProfundidadeCm()
            );
        }

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getEstoque(),
                dimensoesResposta
        );
    }
}