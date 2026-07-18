package br.com.raimundo.estoque.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable causa) {
        super(message, causa);
    }
}
