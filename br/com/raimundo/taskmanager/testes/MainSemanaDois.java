package br.com.raimundo.taskmanager.testes;


import br.com.raimundo.taskmanager.model.Prioridade;
import br.com.raimundo.taskmanager.model.Status;
import br.com.raimundo.taskmanager.model.Task;
import br.com.raimundo.taskmanager.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class MainSemanaDois {

    public static void main(String[] args) {

        List<Task> tarefaGeneric = new ArrayList<>();
        tarefaGeneric.add(new Task(1L, "Estudar List", "Revisar coleções", Status.PENDENTE, Prioridade.ALTA));
        tarefaGeneric.add(new Task(2L, "Estudar generics", "Criar Box e Repositorio", Status.EM_ANDAMENTO, Prioridade.MEDIA));
        tarefaGeneric.add(new Task(3L, "Praticar Map", "Treinar busca por id", Status.CONCLUIDO, Prioridade.BAIXA));

        CollectionUtils.imprimirLista(tarefaGeneric);
        int tamanhoDaLista = CollectionUtils.contarElementos(tarefaGeneric);
        System.out.println(tamanhoDaLista);
    }
}



/* USO DA CLASSE RepositorioSimples
RepositorioSimples<Task> repositorioTask = new RepositorioSimples<>();
        repositorioTask.adicionar(new Task(1L, "Estudar List", "Revisar coleções", Status.PENDENTE, Prioridade.ALTA));
        repositorioTask.adicionar(new Task(2L, "Estudar generics", "Criar Box e Repositorio", Status.EM_ANDAMENTO, Prioridade.MEDIA));
        repositorioTask.adicionar(new Task(3L, "Praticar Map", "Treinar busca por id", Status.CONCLUIDO, Prioridade.BAIXA));

        for (Task task: repositorioTask.listarTodos()) {
            System.out.println(task);
        }*/

//USO DA CLASSE BOX E TASK
/* Box<Task> tarefa = new Box<>();
        tarefa.setValor(new Task(1L,
                "tarefa generic",
                "Teste de uma task no generic",
                Status.PENDENTE,
                Prioridade.ALTA));

        Task task = tarefa.getValor();
        System.out.println(task);*/
