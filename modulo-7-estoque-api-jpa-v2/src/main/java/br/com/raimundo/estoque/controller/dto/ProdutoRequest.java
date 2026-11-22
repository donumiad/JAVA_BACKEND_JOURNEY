package br.com.raimundo.estoque.controller.dto;

import br.com.raimundo.estoque.model.Dimensoes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank String nome,
        @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal preco,
        @Valid DimensoesRequest dimensoes
) {
    public record DimensoesRequest(
            @NotNull @Positive Integer larguraCm,
            @NotNull @Positive Integer alturaCm,
            @NotNull @Positive Integer profundidadeCm
    ) {
    }

    public Dimensoes paraDimensoes() {
        if (dimensoes == null) {
            return null;
        }

        return new Dimensoes(
                dimensoes.larguraCm(),
                dimensoes.alturaCm(),
                dimensoes.profundidadeCm()
        );
    }
}