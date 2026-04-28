package modulos.poo_java.LIST_SET_MAP.classes;

import java.util.ArrayList;
import java.util.List;

public class RepositorioSimples<T> {

    private List<T> itens = new ArrayList<>();

    public void adicionar(T item) {
        itens.add(item);
    }

    public List<T> listarTodos() {
        return itens;
    }
}
