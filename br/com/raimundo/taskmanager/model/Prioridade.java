package br.com.raimundo.taskmanager.model;

public enum Prioridade {
    URGENTE(5),
    ALTA(4),
    MEDIA(3),
    BAIXA(2),
    MINIMA(1);

    private final int peso;

    Prioridade(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }
}
