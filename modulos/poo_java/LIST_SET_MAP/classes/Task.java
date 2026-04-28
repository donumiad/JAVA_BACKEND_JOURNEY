package modulos.poo_java.LIST_SET_MAP.classes;

import modulos.poo_java.LIST_SET_MAP.enumclasses.Prioridade;
import modulos.poo_java.LIST_SET_MAP.enumclasses.Status;

import java.util.Objects;

public class Task {
    private Long id;
    private String titulo;
    private String descricao;
    private Status status;
    private Prioridade prioridade;

    public Task(Long id, String titulo, String descricao, Status status, Prioridade prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Status getStatus() {
        return status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", prioridade=" + prioridade +
                '}';
    }
}
