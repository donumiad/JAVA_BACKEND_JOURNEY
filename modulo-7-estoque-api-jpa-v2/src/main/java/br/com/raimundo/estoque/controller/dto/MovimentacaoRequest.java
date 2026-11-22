package br.com.raimundo.estoque.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentacaoRequest(
        @NotNull @Positive Long produtoId,
        @NotNull @Positive Long quantidade
) {
}