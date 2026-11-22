package br.com.raimundo.estoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Dimensoes {

    @Column(name = "largura_cm")
    private int larguraCm;

    @Column(name = "altura_cm")
    private int alturaCm;

    @Column(name = "profundidade_cm")
    private int profundidadeCm;

    protected Dimensoes() {
    }

    public Dimensoes(int larguraCm, int alturaCm, int profundidadeCm) {
        if (larguraCm < 0 || alturaCm < 0 || profundidadeCm < 0) {
            throw new IllegalArgumentException(
                    "Largura, altura e profundidade devem ser maiores que zero."
            );
        }

        this.larguraCm = larguraCm;
        this.alturaCm = alturaCm;
        this.profundidadeCm = profundidadeCm;
    }

    public int getLarguraCm() {
        return larguraCm;
    }

    public int getAlturaCm() {
        return alturaCm;
    }

    public int getProfundidadeCm() {
        return profundidadeCm;
    }
}