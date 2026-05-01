package br.com.raimundo.taskmanager.app;

import br.com.raimundo.taskmanager.comparator.TaskPorDataComparator;
import br.com.raimundo.taskmanager.comparator.TaskPorPrioridadeComparator;
import br.com.raimundo.taskmanager.comparator.TaskPorTituloComparator;
import br.com.raimundo.taskmanager.model.Prioridade;
import br.com.raimundo.taskmanager.model.Status;
import br.com.raimundo.taskmanager.model.Task;
import br.com.raimundo.taskmanager.testes.Produto;
import br.com.raimundo.taskmanager.testes.SetProduto;

import java.time.LocalDate;
import java.util.*;

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

        List<Task> tarefas = new ArrayList<>();

        tarefas.add(new Task(
                1L,
                "Trabalhar List",
                "Aprender listas",
                Status.PENDENTE,
                Prioridade.ALTA,
                LocalDate.of(2026,5,8)));
        tarefas.add(new Task(
                2L,
                "Preparar Set",
                "Aprender conjuntos",
                Status.EM_ANDAMENTO,
                Prioridade.MEDIA,
                LocalDate.of(2026,5,6)));
        tarefas.add(new Task(3L,
                "Estudar Map",
                "Aprender mapeamentos",
                Status.PENDENTE,
                Prioridade.BAIXA,
                LocalDate.of(2026,4,30)));
        tarefas.add(new Task(
                4L,
                "Criar enum",
                "Treinar Status e Prioridade",
                Status.CONCLUIDO,
                Prioridade.MINIMA,
                LocalDate.of(2026,4,25)));
        tarefas.add(new Task(
                5L,
                "Analisar toString",
                "Melhorar impressão de objetos",
                Status.PENDENTE,
                Prioridade.URGENTE,
                LocalDate.of(2026,5,10)));

        System.out.println("ANTES DA ORDENACAO");
        for (Task listaTask: tarefas) {
            System.out.println(listaTask);
        }

        Collections.sort(tarefas, new TaskPorDataComparator());
        System.out.println("DEPOIS");
        for (Task listaTask: tarefas) {
            System.out.println(listaTask);
        }

    }
}


/*
*
* List<String> nomes = new ArrayList<>();

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
* */