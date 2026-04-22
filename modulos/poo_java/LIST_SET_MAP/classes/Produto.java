package modulos.poo_java.LIST_SET_MAP.classes;

public class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{nome='" + nome + "', preco=" + preco + "}";
    }
}
