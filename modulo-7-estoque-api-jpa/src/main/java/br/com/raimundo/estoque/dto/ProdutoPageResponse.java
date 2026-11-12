package br.com.raimundo.estoque.dto;

import br.com.raimundo.estoque.model.PaginaProduto;

import java.util.List;
//entender como ele se relaciona com o resto do codigo
public record ProdutoPageResponse(
        List<ProdutoResponse> itens,
        int page,
        int size,
        long total,
        boolean proximaPagina
) {

    public static ProdutoPageResponse from(
            PaginaProduto pagina
    ) {

        List<ProdutoResponse> itens =
                pagina.itens()
                        .stream()
                        .map(ProdutoResponse::from)
                        .toList();

        return new ProdutoPageResponse(
                itens,
                pagina.page(),
                pagina.size(),
                pagina.total(),
                pagina.proximaPagina()
        );
    }
}