package br.com.raimundo.estoque.model;

import java.util.List;

public record PaginaProduto(
        List<Produto> itens,
        int page,
        int size,
        long total,
        boolean proximaPagina
){
}
