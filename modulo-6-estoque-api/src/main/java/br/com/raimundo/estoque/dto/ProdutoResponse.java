package br.com.raimundo.estoque.dto;

import br.com.raimundo.estoque.model.Produto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Dados públicos de um produto")
public record ProdutoResponse(
        @Schema(example = "1")
        Long id,

        @Schema(example = "Mouse sem fio")
        String nome,

        @Schema(example = "149.90")
        BigDecimal preco,

        @Schema(example = "10")
        Integer estoque
) {


    public static ProdutoResponse from(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getEstoque()
        );
    }
}