package br.com.raimundo.estoque.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "movimentacoes_estoque")
public class Movimentacao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movimentacoes_seq"
    )
    @SequenceGenerator(
            name = "movimentacoes_seq",
            sequenceName = "movimentacoes_estoque_id_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "produto_id", insertable = false, updatable = false)
    private Long produtoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "tipo_movimentacao", nullable = false, length = 10)
    private String tipo;

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    protected Movimentacao() {}

    public static Movimentacao entrada(Produto produto, Long quantidade) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.produto = produto;
        movimentacao.tipo = "ENTRADA";
        movimentacao.quantidade = quantidade;
        movimentacao.dataMovimentacao = LocalDateTime.now();
        return movimentacao;
    }

    public Movimentacao(
            Long produtoId,
            String tipo,
            Long quantidade
    ) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public Movimentacao(
            Long id,
            Long produtoId,
            String tipo,
            Long quantidade,
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

    public Produto getProduto() {
        return produto;
    }
    public Long getProdutoId() {
        if (produto != null) {
            return produto.getId();
        }
        return produtoId;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getQuantidade() {
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
