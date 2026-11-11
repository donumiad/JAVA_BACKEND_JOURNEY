
package br.com.raimundo.estoque.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {

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

    protected ProdutoEntity() {
        // Utilizado pelo mecanismo de persistência.
    }

    public ProdutoEntity(
            String nome,
            BigDecimal preco,
            Long estoque
    ) {
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
}
