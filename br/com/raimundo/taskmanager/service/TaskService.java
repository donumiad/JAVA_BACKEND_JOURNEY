package br.com.raimundo.taskmanager.service;

import br.com.raimundo.taskmanager.comparator.TaskPorDataComparator;
import br.com.raimundo.taskmanager.exception.DuplicateTaskException;
import br.com.raimundo.taskmanager.exception.InvalidTaskDataException;
import br.com.raimundo.taskmanager.exception.TaskNotFoundException;
import br.com.raimundo.taskmanager.model.Prioridade;
import br.com.raimundo.taskmanager.model.Status;
import br.com.raimundo.taskmanager.model.Task;
import br.com.raimundo.taskmanager.repository.TaskRepositoryMemoria;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {

    private final TaskRepositoryMemoria repositorio;

    public TaskService(TaskRepositoryMemoria repositorio) {
        this.repositorio = repositorio;
    }

    /*
     * Filtrar por ID/ TITULO/ DESCRIÇÃO/ STATUS/ PRIORIDADE/ DATA LIMITE
     * Estes filtros ja cobrem a maioria das necessidades de relatórios, pois pega cada um individual
     * e pode ser capurado a quantidade com o SIZE()
     * */


    //***********METODOS QUE TRABALHAM COM ID*********************************

    public Optional<Task> buscarUnicaPorId(Long id) {
        if (id == null){
            throw new InvalidTaskDataException("ID não pode ser nulo");
        }
        return repositorio.buscarPorId(id);
    }

    public Task buscarObrigatoriaPorId(Long id){
        if (id == null){
            throw new InvalidTaskDataException("ID não pode ser nulo");
        }
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));
    }

    //*************************************************************************
    //***********METODOS QUE TRABALHAM COM TÍTULO******************************

    public List<String> listarTodosOsTitulos() {
        return repositorio.listarTodas()
                .stream()
                .map(Task::getTitulo)
                .toList();
    }

    public List<Task> buscarPorTitulo(String trecho) {
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getTitulo().toLowerCase().contains(trecho.toLowerCase()))
                .toList();
    }

   /* public List<String> listarTitulosEmCaixaAlta() {
        return repositorio.listarTodas()
                .stream()
                .map(task -> task.getTitulo().toUpperCase())
                .toList();
    }*/

    //****************************************************************************
    //***********METODOS QUE TRABALHAM COM DESCRIÇÃO******************************

    public List<String> listarTodasDescricoes() {
        return repositorio.listarTodas()
                .stream()
                .map(Task::getDescricao)
                .toList();
    }

    public List<Task> buscarPorDescricao(String trecho) {
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getDescricao().toLowerCase().contains(trecho.toLowerCase()))
                .toList();
    }


    //****************************************************************************
    //***********METODOS QUE TRABALHAM COM STATUS******************************

    public List<Task> filtrarPorStatus(Status status) {
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    //Visão geral das tarefas por STATUS
    public Map<Status, List<Task>> listarTarefasPorStatus() {
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus));
    }

    //Visão geral da quatidade de tarefas separado por STATUS
    public Map<Status, Long> conteTodosPorStatus() {
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }


    //****************************************************************************
    //***********METODOS QUE TRABALHAM COM PRIORIDADE******************************

    public List<Task> filtrarPorPrioridade(Prioridade prioridade) {
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getPrioridade() == prioridade)
                .toList();

    }

    //Visão geral das tarefas por PRIORIDADE
    public Map<Prioridade, List<Task>> tarefasPorPrioridade() {
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getPrioridade));
    }

    //Visão geral da quatidade de tarefas separado por PRIORIDADE
    public Map<Prioridade, Long> contePorPrioridade() {
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getPrioridade, Collectors.counting()));
    }

    /*public Set<Prioridade> listarPrioridades() {
        return repositorio.listarTodas()
                .stream()
                .map(Task::getPrioridade)
                .collect(Collectors.toSet());
    }*/


    //****************************************************************************
    //***********METODOS QUE TRABALHAM COM DATA******************************

    public List<Task> filtrarVencidas() {
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getDataLimite().isBefore(LocalDate.now()))
                .toList();
    }

    //Visão geral das tarefas por DATA
    public Map<LocalDate, List<Task>> tarefasPorDataLimite() {
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getDataLimite));
    }

    //Visão geral da quatidade de tarefas separado por DATA
    public Map<LocalDate, Long> contePorDataLimite() {
        return repositorio.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(Task::getDataLimite, Collectors.counting()));
    }


    public List<Task> listarPorDataOrdenada() {
        return repositorio.listarTodas()
                .stream()
                .sorted(new TaskPorDataComparator())
                .toList();
    }

    //****************************************************************************
    //****************************METODOS ACESSÓRIOS******************************


    public void cadastrar(Task task) {
        if (task == null) {
            throw new InvalidTaskDataException("A tarefa não pode ser nula.");
        }

        if (task.getId() == null) {
            throw new InvalidTaskDataException("O id da tarefa não pode ser nulo.");
        }

        if (task.getTitulo() == null || task.getTitulo().isBlank()) {
            throw new InvalidTaskDataException("O título da tarefa não pode ser vazio.");
        }

        if (repositorio.buscarPorId(task.getId()).isPresent()) {
            throw new DuplicateTaskException("Já existe uma tarefa com id: " + task.getId());
        }

        repositorio.adicionar(task);
    }

    public List<String> listarResumosFormatados() {
        return repositorio.listarTodas()
                .stream()
                .map(task -> task.getTitulo() + " - "
                        + task.getPrioridade() + " - "
                        + task.getDataLimite())
                .toList();
    }

    public Optional<Task> buscarMaisUrgente() {

        List<Task> tarefas = repositorio.listarTodas();

        Optional<Prioridade> maiorPrioridade = tarefas.stream()
                .map(Task::getPrioridade)
                .max(Comparator.comparingInt(Prioridade::getPeso));

        if (maiorPrioridade.isEmpty()) {
            return Optional.empty();
        }
        List<Task> tarefasDeMaiorPrioridade = tarefas.stream()
                .filter(task -> task.getPrioridade() == maiorPrioridade.get())
                .toList();
        return tarefasDeMaiorPrioridade.stream().min(new TaskPorDataComparator());
    }
}

/*
    public List<Task> listarPendentes() {
        return repositorio.listarTodas()
                .stream()
                .filter(task -> task.getStatus() == Status.PENDENTE)
                .toList();
    }*/
