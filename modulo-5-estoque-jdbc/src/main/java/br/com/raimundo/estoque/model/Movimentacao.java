package br.com.raimundo.estoque.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Movimentacao {
    private Long id;
    private Long produtoId;
    private String tipo;
    private Integer quantidade;
    private LocalDateTime dataHora;

    public Movimentacao(Long produtoId, String tipo,
                        Integer quantidade) {
        this.produtoId   = produtoId;
        this.tipo        = tipo;
        this.quantidade  = quantidade;
    }

    public Movimentacao(Long id, Long produtoId, String tipo,
                        Integer quantidade, LocalDateTime dataHora) {
        this.id          = id;
        this.produtoId   = produtoId;
        this.tipo        = tipo;
        this.quantidade  = quantidade;
        this.dataHora    = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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
}
