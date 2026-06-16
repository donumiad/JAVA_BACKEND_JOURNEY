package br.com.raimundo.loja.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private Long id;
    private LocalDate dataPedido;
    private Cliente cliente;

    public Pedido(Long id,LocalDate dataPedido, Cliente cliente) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id  +
                ", cliente=" + cliente.getNome() +
                ", data do pedido=" + dataPedido +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
