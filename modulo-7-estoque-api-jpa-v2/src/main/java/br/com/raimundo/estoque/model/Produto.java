package br.com.raimundo.estoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(
            name = "produto_seq",
            sequenceName = "produtos_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "nome", nullable = false, length = 300)
    private String nome;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "estoque", nullable = false)
    private long estoque;

    @Embedded
    private Dimensoes dimensoes;

    protected Produto() {
    }

    public Produto(String nome, BigDecimal preco, Dimensoes dimensoes) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        String nomeLimpo = nome.strip();
        if (nomeLimpo.codePointCount(0, nomeLimpo.length()) > 300) {
            throw new IllegalArgumentException("O nome deve ter até 300 caracteres.");
        }

        if (preco == null || preco.signum() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        if (preco.scale() > 2 || preco.compareTo(new BigDecimal("100000000")) >= 0) {
            throw new IllegalArgumentException(
                    "O preço deve ter até oito dígitos inteiros e duas casas decimais."
            );
        }

        this.nome = nomeLimpo;
        this.preco = preco;
        this.estoque = 0;
        this.dimensoes = dimensoes;
    }

    public void adicionarEstoque(long quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.estoque = Math.addExact(this.estoque, quantidade);
    }

    public boolean retirarEstoque(long quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (quantidade > this.estoque) {
            return false;
        }
        this.estoque -= quantidade;
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public long getEstoque() {
        return estoque;
    }

    public Dimensoes getDimensoes() {
        return dimensoes;
    }
}