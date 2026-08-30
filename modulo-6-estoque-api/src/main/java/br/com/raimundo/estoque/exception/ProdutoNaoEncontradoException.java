package br.com.raimundo.estoque.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id) {
      super("Produto de ID " + id + " não encontrado");
    }
}
