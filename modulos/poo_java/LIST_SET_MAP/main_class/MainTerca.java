package modulos.poo_java.LIST_SET_MAP.main_class;

import modulos.poo_java.LIST_SET_MAP.classes.Task;

import java.util.ArrayList;
import java.util.List;

import modulos.poo_java.LIST_SET_MAP.enumclasses.Prioridade;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Status;



public class MainTerca {
    public static void main(String[] args) {
        List<Task> tarefas = new ArrayList<>();

        //tarefas.add(1,"primeira tarefa", "teste da task", NAO_INICIADO, ALTA);

        Task task1 = new Task(1,
                "primeira task",
                "treino do list",
                Status.NAO_INICIADO,
                Prioridade.ALTA);

        Task task12 = new Task(2,
                "segunda task",
                "treino do list 2",
                Status.EM_ANDAMENTO,
                Prioridade.URGENTE);

        Task task3 = new Task(3,
                "terceira task",
                "treino do list 3",
                Status.CONCLUIDO,
                Prioridade.BAIXA);

        List<Task> tasks = new ArrayList<>();

        tasks.add(task1);
        tasks.add(task12);
        tasks.add(task3);

        for(Task task: tasks ){
            System.out.println(task);
        }

    }
}
