package modulos.poo_java.LIST_SET_MAP.main_class;

import modulos.poo_java.LIST_SET_MAP.classes.Box;
import modulos.poo_java.LIST_SET_MAP.classes.RepositorioSimples;
import modulos.poo_java.LIST_SET_MAP.classes.Task;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Prioridade;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Status;

public class MainSemanaDois {

    public static void main(String[] args) {

        RepositorioSimples<Task> repositorioTask = new RepositorioSimples<>();
        repositorioTask.adicionar(new Task(1L, "Estudar List", "Revisar coleções", Status.PENDENTE, Prioridade.ALTA));
        repositorioTask.adicionar(new Task(2L, "Estudar generics", "Criar Box e Repositorio", Status.EM_ANDAMENTO, Prioridade.MEDIA));
        repositorioTask.adicionar(new Task(3L, "Praticar Map", "Treinar busca por id", Status.CONCLUIDO, Prioridade.BAIXA));

        for (Task task: repositorioTask.listarTodos()) {
            System.out.println(task);
        }
    }
}
//USO DA CLASSE BOX E TASK
/* Box<Task> tarefa = new Box<>();
        tarefa.setValor(new Task(1L,
                "tarefa generic",
                "Teste de uma task no generic",
                Status.PENDENTE,
                Prioridade.ALTA));

        Task task = tarefa.getValor();
        System.out.println(task);*/
