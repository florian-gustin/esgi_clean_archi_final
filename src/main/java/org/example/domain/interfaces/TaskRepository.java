package org.example.domain.interfaces;

import org.example.domain.Task;
import org.example.domain.Tasks;

public interface TaskRepository {

    Tasks getAll();
    Tasks update(Task task);

    boolean remove(String id);

}
