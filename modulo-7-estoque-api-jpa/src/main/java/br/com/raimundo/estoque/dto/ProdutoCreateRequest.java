package br.com.raimundo.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Dados necessários para cadastrar um produto")
public record ProdutoCreateRequest(

        @Schema(
                description = "Nome do produto",
                example = "Mouse sem fio"
        )
        @NotBlank
        String nome,

        @Schema(
                description = "Preço unitário do produto",
                example = "149.90"
        )
        @NotNull
        @Positive
        BigDecimal preco,

        @Schema(
                description = "Quantidade inicial disponível em estoque",
                example = "10"
        )
        @NotNull
        @PositiveOrZero
        Integer estoque){}
