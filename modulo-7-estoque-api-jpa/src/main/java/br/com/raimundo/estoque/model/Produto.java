package br.com.raimundo.estoque.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "produto_seq"
    )
    @SequenceGenerator(
            name = "produto_seq",
            sequenceName = "produtos_id_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 300)
    private String nome;

    @Column(
            name = "preco",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal preco;

    @Column(name = "estoque", nullable = false)
    private Long estoque;

    protected Produto() {
        // Utilizado pelo mecanismo de persistência.
    }

    public Produto(
            Long id,
            String nome,
            BigDecimal preco,
            Long estoque
    ) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Long getEstoque() {
        return estoque;
    }

    public void setEstoque(Long estoque) {
        this.estoque = estoque;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
