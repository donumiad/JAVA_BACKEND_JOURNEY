package br.com.raimundo.taskmanager.comparator;

import java.util.Comparator;
import br.com.raimundo.taskmanager.model.Task;

public class TaskPorTituloComparator implements Comparator<Task>{
    @Override
    public int compare(Task t1, Task t2){
        return t1.getTitulo().compareTo(t2.getTitulo());
    }
}
