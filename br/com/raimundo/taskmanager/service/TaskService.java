package br.com.raimundo.taskmanager.service;

import br.com.raimundo.taskmanager.comparator.TaskPorDataComparator;
import br.com.raimundo.taskmanager.model.Prioridade;
import br.com.raimundo.taskmanager.model.Status;
import br.com.raimundo.taskmanager.model.Task;
import br.com.raimundo.taskmanager.repository.TaskRepositoryMemoria;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {

    private TaskRepositoryMemoria repositorio;

    public TaskService (TaskRepositoryMemoria repositorio){
        this.repositorio = repositorio;
    }

    public List<Task> filtrarPorStatus(Status status){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());

    }
    public List<Task> filtrarPorPrioridade(Prioridade prioridade){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getPrioridade().equals(prioridade))
                .collect(Collectors.toList());

    }
    public List<Task> filtrarVencidas(){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getDataLimite().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }
    public List<String> listarTitulos(){
        return repositorio.listarTodas()
                .stream()
                .map(task -> task.getTitulo())
                .toList();
    }
    public List<String> listarDescricoes(){
        return repositorio.listarTodas()
                .stream()
                .map(task -> task.getDescricao())
                .toList();
    }
    public List<String> listarResumosFormatados(){
        return repositorio.listarTodas()
                .stream()
                .map(task -> task.getTitulo() + " - "
                                + task.getPrioridade() + " - "
                                + task.getDataLimite())
                .toList();
    }
    public List<String> listarTitulosEmCaixaAlta(){
        return repositorio.listarTodas()
                .stream()
                .map(task -> task.getTitulo().toUpperCase())
                .toList();
    }
    public List<Task> listarPendentes(){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getStatus() == Status.PENDENTE)
                .toList();
    }
    public Set<Prioridade> listarPrioridades(){
        return repositorio.listarTodas()
                .stream()
                .map(task -> task.getPrioridade())
                .collect(Collectors.toSet());
    }
    public List<Task> listarPorDaraOrdenada(){
        return repositorio.listarTodas()
                .stream()
                .sorted(new TaskPorDataComparator())
                .toList();
    }

    //EXERCICIOS DO DIA 08/05/2026

    public List<Task> tarefaComPalavraDentroDoTitulo(String palavra){
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getTitulo().contains(palavra))
                .toList();
    }

    //EXERCICIO DIA 12/05
    public Map<Status , List<Task>> tarefasPorStatus(){
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus));
    }
    public Map<Prioridade, List<Task>> tarefasPorPrioridade(){
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getPrioridade));
    }
    public Map<Status , Long> contePorStatus(){
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }
    public Map<Prioridade , Long> contePorPrioridade(){
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getPrioridade, Collectors.counting()));
    }

}
