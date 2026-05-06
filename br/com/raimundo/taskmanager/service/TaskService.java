package br.com.raimundo.taskmanager.service;

import br.com.raimundo.taskmanager.model.Prioridade;
import br.com.raimundo.taskmanager.model.Status;
import br.com.raimundo.taskmanager.model.Task;
import br.com.raimundo.taskmanager.repository.TaskRepositoryMemoria;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private TaskRepositoryMemoria repositorio;

    public TaskService (TaskRepositoryMemoria repositorio){
        this.repositorio = repositorio;
    }

    public List<Task> listarPorStatus(Status status){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());

    }
    public List<Task> listarPorPrioridade(Prioridade prioridade){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getPrioridade().equals(prioridade))
                .collect(Collectors.toList());

    }
    public List<Task> listarVencidas(){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getDataLimite().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
