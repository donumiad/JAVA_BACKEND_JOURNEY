package br.com.raimundo.taskmanager.util;

public class Box<T> {

    private T valor;

    public void setValor(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }
}
