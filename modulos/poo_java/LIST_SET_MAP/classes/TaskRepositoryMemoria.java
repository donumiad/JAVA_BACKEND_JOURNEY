package modulos.poo_java.LIST_SET_MAP.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskRepositoryMemoria {
    private List<Task> tarefas = new ArrayList<>();

    public void adicionar(Task task){
        tarefas.add(task);
    }

    public void listarTodas(){
        for (Task listaTarefa: tarefas) {
            System.out.println(listaTarefa);
        }
    }

    public Task buscaPorId(Long id){
        for (Task task: tarefas) {
            if (task.getId().equals(id)){return task;}
        }
        return null;
    }

    public void removerPorId(Long id){
        Task taskEncontrada = buscaPorId(id);
        if (taskEncontrada != null) {
            tarefas.remove(taskEncontrada);
        }
    }
}
