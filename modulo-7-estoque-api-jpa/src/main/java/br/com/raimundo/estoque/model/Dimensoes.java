package br.com.raimundo.estoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Dimensoes(
        @Column(name = "largura_cm") int larguraCm,
        @Column(name = "altura_cm") int alturaCm,
        @Column(name = "profundidade_cm") int profundidadeCm
) {
    public Dimensoes {
        if (larguraCm <= 0 || alturaCm <= 0 || profundidadeCm <= 0) {
            throw new IllegalArgumentException("As dimensões devem ser positivas.");
        }
    }
}