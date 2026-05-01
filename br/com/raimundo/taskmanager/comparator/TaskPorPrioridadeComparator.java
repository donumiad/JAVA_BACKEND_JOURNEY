package br.com.raimundo.taskmanager.comparator;

import br.com.raimundo.taskmanager.model.Task;
import java.util.Comparator;

public class TaskPorPrioridadeComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2){

        return t1.getPrioridade().compareTo(t2.getPrioridade());
    }
}
