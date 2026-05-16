package br.com.raimundo.taskmanager.repository;

import br.com.raimundo.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryMemoria {
    private List<Task> tarefas = new ArrayList<>();

    public void adicionar(Task task){
        tarefas.add(task);
    }

    public List<Task> listarTodas(){
        return tarefas;
    }

    public Optional<Task> buscarPorId(Long id){
        return tarefas.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public void removerPorId(Long id){
        Optional<Task> taskEncontrada = buscarPorId(id);
        if (taskEncontrada.isPresent()) {
            tarefas.remove(taskEncontrada);
        }
    }
}
