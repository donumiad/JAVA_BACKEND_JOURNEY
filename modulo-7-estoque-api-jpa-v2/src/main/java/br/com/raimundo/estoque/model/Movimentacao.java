package br.com.raimundo.estoque.model;

import br.com.raimundo.estoque.model.enums.TipoMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "movimentacoes_estoque")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimentacao_seq")
    @SequenceGenerator(
            name = "movimentacao_seq",
            sequenceName = "movimentacoes_estoque_id_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false, length = 10)
    private TipoMovimentacao tipo;

    @Column(name = "quantidade", nullable = false)
    private long quantidade;

    @Column(name = "data_movimentacao", nullable = false)
    private Instant dataMovimentacao;

    protected Movimentacao() {
    }

    public static Movimentacao entrada(Produto produto, long quantidade) {
        return criar(produto, quantidade, TipoMovimentacao.ENTRADA);
    }

    public static Movimentacao saida(Produto produto, long quantidade) {
        return criar(produto, quantidade, TipoMovimentacao.SAIDA);
    }

    private static Movimentacao criar(
            Produto produto, long quantidade, TipoMovimentacao tipo) {

        if (produto == null) {
            throw new IllegalArgumentException("Informe o produto da movimentação.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.produto = produto;
        movimentacao.tipo = tipo;
        movimentacao.quantidade = quantidade;
        movimentacao.dataMovimentacao = Instant.now();
        return movimentacao;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public Instant getDataMovimentacao() {
        return dataMovimentacao;
    }
}