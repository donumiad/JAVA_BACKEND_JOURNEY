package br.com.raimundo.estoque.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentacaoRequest(
        @NotNull
        @Positive
        long quantidade) { }
