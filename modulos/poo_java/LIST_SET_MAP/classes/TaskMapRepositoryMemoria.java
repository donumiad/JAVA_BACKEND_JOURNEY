package modulos.poo_java.LIST_SET_MAP.classes;

import java.util.HashMap;
import java.util.Map;

public class TaskMapRepositoryMemoria {
    private Map<Long, Task> tarefas = new HashMap();

    public void adicionar(Long id, Task task){
        tarefas.put(id, task);
    }

    public void listarTodas(){
        for (Map.Entry<Long, Task> listaTarefa: tarefas.entrySet()) {
            System.out.println(listaTarefa.getValue());
        }
        System.out.println("tarefa sozinho");
        System.out.println(tarefas);
    }

    public Task buscaPorId(Long id){
        if(tarefas.get(id).equals(null)){return null;}
        return tarefas.get(id);
    }

    public void removerPorId(Long id){
        tarefas.remove(id);
    }
}
