package br.com.raimundo.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoUpdateRequest (
        @NotBlank
        String nome,

        @NotNull
        @Positive
        BigDecimal preco
){ }
