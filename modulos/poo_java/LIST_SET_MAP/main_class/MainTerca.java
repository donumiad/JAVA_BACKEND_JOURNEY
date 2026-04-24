package modulos.poo_java.LIST_SET_MAP.main_class;


/*
CODIGO PARA LIST E SET

import java.util.ArrayList;
import java.util.List;
import modulos.poo_java.LIST_SET_MAP.classes.TaskRepositoryMemoria;

*/
import modulos.poo_java.LIST_SET_MAP.classes.Task;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Prioridade;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Status;
import modulos.poo_java.LIST_SET_MAP.classes.TaskMapRepositoryMemoria;

public class MainTerca {
    public static void main(String[] args) {
        TaskMapRepositoryMemoria repositorio = new TaskMapRepositoryMemoria();


        repositorio.adicionar(1L, new Task(1L, "Estudar List", "Aprender listas", Status.PENDENTE, Prioridade.ALTA));
        repositorio.adicionar(2L, new Task(1L, "Estudar Set", "Aprender conjuntos", Status.EM_ANDAMENTO, Prioridade.MEDIA));
        repositorio.adicionar(3L, new Task(3L, "Estudar Map", "Aprender mapeamentos", Status.PENDENTE, Prioridade.ALTA));
        repositorio.adicionar(4L, new Task(4L, "Praticar enum", "Treinar Status e Prioridade", Status.CONCLUIDO, Prioridade.BAIXA));
        repositorio.adicionar(5L, new Task(5L, "Revisar toString", "Melhorar impressão de objetos", Status.PENDENTE, Prioridade.MEDIA));

        repositorio.listarTodas();
        System.out.println("");
        repositorio.removerPorId(3L);
        repositorio.listarTodas();
        System.out.println("");

        /*
        CODIGO PARA LIST E SET
        TaskRepositoryMemoria repositorio = new TaskRepositoryMemoria();
        repositorio.adicionar(new Task(1L, "Estudar List", "Aprender listas", Status.PENDENTE, Prioridade.ALTA));
        repositorio.adicionar(new Task(1L, "Estudar Set", "Aprender conjuntos", Status.EM_ANDAMENTO, Prioridade.MEDIA));
        repositorio.adicionar(new Task(3L, "Estudar Map", "Aprender mapeamentos", Status.PENDENTE, Prioridade.ALTA));
        repositorio.adicionar(new Task(4L, "Praticar enum", "Treinar Status e Prioridade", Status.CONCLUIDO, Prioridade.BAIXA));
        repositorio.adicionar(new Task(5L, "Revisar toString", "Melhorar impressão de objetos", Status.PENDENTE, Prioridade.MEDIA));

        repositorio.listarTodas();
        repositorio.removerPorId(3L);
        repositorio.listarTodas();
        */

    }
}


