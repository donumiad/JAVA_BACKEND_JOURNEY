package br.com.raimundo.estoque.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(
            Long estoqueAtual,
            Long quantidadeSolicitada) {
        super("Estoque insuficiente. Disponével: "
                + estoqueAtual
                +", solicitado: "
                + quantidadeSolicitada);
    }
}
