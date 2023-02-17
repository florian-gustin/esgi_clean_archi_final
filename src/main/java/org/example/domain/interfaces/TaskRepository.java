package org.example.domain.interfaces;

import org.example.domain.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> getTasks();
    void saveTask(List<Task> tasks);

}
