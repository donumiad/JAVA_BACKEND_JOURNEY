package modulos.poo_java.LIST_SET_MAP.main_class;

import modulos.poo_java.LIST_SET_MAP.classes.Task;

import java.util.ArrayList;
import java.util.List;

import modulos.poo_java.LIST_SET_MAP.classes.TaskRepositoryMemoria;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Prioridade;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Status;



public class MainTerca {
    public static void main(String[] args) {
        TaskRepositoryMemoria repositorio = new TaskRepositoryMemoria();

        repositorio.adicionar(new Task(1L, "Estudar List", "Aprender listas", Status.PENDENTE, Prioridade.ALTA));
        repositorio.adicionar(new Task(2L, "Estudar Set", "Aprender conjuntos", Status.EM_ANDAMENTO, Prioridade.MEDIA));
        repositorio.adicionar(new Task(3L, "Estudar Map", "Aprender mapeamentos", Status.PENDENTE, Prioridade.ALTA));
        repositorio.adicionar(new Task(4L, "Praticar enum", "Treinar Status e Prioridade", Status.CONCLUIDO, Prioridade.BAIXA));
        repositorio.adicionar(new Task(5L, "Revisar toString", "Melhorar impressão de objetos", Status.PENDENTE, Prioridade.MEDIA));

        repositorio.listarTodas();
        repositorio.removerPorId(3L);
        repositorio.listarTodas();


    }
}


