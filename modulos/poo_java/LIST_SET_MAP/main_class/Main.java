package modulos.poo_java.LIST_SET_MAP.main_class;

import modulos.poo_java.LIST_SET_MAP.classes.Produto;
import modulos.poo_java.LIST_SET_MAP.classes.SetProduto;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/*LIST
* mantém ordem de inserção na maioria dos usos práticos
* permite elementos repetidos
* acessa por índice
* list.add() adiciona um elemento no fim da lista
* list.get(posição) retorna o elemento na posição indicada
* list.remove(elemento/posição) remove o elemento/posição indicado
* list.size() retorna o tamanho da lista em numero de elementos
* list.set(posição, elemento) altera o elemento da posíção indicada
* list.contains(elemento) verifica se o elemento esta contido na lista
* list.isEmpty() verifica se a lista esta vazia
* list.clear() remove todos os elementos
* */

public class Main {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();

        nomes.add("Ana");
        nomes.add("Carlos");
        nomes.add("João");

        System.out.println(nomes);

//-------------List Codigo-----------------
        System.out.println("LIST CODIGO");
        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Teclado", 120.0));
        produtos.add(new Produto("Mouse", 80.0));
        produtos.add(new Produto("Monitor", 900.0));

        for (Produto produto : produtos) {
            System.out.println(produto);
        }

//-------------------SET CODIGO-------------------------
        System.out.println("SET CODIGO");
        Set<SetProduto> setprodutos = new HashSet<>();

        setprodutos.add(new SetProduto("P001", "Teclas"));
        setprodutos.add(new SetProduto("P001", "Teclas Mecânico"));
        setprodutos.add(new SetProduto("P002", "Mouses"));

        System.out.println(setprodutos.size());

        for (SetProduto setproduto : setprodutos) {
            System.out.println(setproduto);
        }
    }
}