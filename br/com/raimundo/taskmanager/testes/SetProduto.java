package br.com.raimundo.taskmanager.testes;


import java.util.Objects;


public class SetProduto {
    private String codigo;
    private String nome;

    public SetProduto(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetProduto produto = (SetProduto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Produto{codigo='" + codigo + "', nome='" + nome + "'}";
    }
}
