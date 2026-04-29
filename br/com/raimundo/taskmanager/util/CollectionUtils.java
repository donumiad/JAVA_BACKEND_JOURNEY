package br.com.raimundo.taskmanager.util;

import java.util.List;

public class CollectionUtils {

    public static <T> void imprimirLista(List<T> lista) {
        for (T elemento : lista) {
            System.out.println(elemento);
        }
    }

    public static <T> int contarElementos(List<T> lista) {
        return lista.size();
    }
}
