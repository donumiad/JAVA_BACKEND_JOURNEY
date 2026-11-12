package br.com.raimundo.estoque.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Movimentacao {

    private Long id;
    private Long produtoId;
    private String tipo;
    private Integer quantidade;
    private LocalDateTime dataMovimentacao;

    public Movimentacao(
            Long produtoId,
            String tipo,
            Integer quantidade
    ) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public Movimentacao(
            Long id,
            Long produtoId,
            String tipo,
            Integer quantidade,
            LocalDateTime dataMovimentacao
    ) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
    }

    public Long getId() {
        return id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movimentacao that = (Movimentacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", tipo='" + tipo + '\'' +
                ", quantidade=" + quantidade +
                ", dataMovimentacao=" + dataMovimentacao +
                '}';
    }
}
