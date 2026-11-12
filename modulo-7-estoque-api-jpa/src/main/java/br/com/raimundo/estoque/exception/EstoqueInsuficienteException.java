package br.com.raimundo.estoque.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(
            Integer estoqueAtual,
            Integer quantidadeSolicitada) {
        super("Estoque insuficiente. Disponével: "
                + estoqueAtual
                +", solicitado: "
                + quantidadeSolicitada);
    }
}
