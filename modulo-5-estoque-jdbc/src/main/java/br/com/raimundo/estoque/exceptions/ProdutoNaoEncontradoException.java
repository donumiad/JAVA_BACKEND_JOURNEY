package br.com.raimundo.estoque.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {

        public ProdutoNaoEncontradoException(Long id) {
            super("Produto não encontrado: " + id);
        }

}
